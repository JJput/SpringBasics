package com.twj.spirngbasics.user.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.twj.spirngbasics.server.config.RabbitMqConfig;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.entity.BaseEntity;
import com.twj.spirngbasics.server.entity.User;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.manage.RedisManage;
import com.twj.spirngbasics.server.manage.UserManage;
import com.twj.spirngbasics.server.mq.RabbitProducer;
import com.twj.spirngbasics.server.util.Constant;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.EncryptUtils;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import com.twj.spirngbasics.user.dto.UserDto;
import com.twj.spirngbasics.user.dto.UserRoleUserDto;
import com.twj.spirngbasics.user.mapper.UserDynamicSqlSupport;
import com.twj.spirngbasics.user.mapper.UserMapper;
import com.twj.spirngbasics.user.mapper.UserRoleUserDynamicSqlSupport;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.StringJoiner;

import static com.twj.spirngbasics.server.util.Constant.AliyunSMSTemplateCode.CHANGE_FLAG;
import static com.twj.spirngbasics.server.util.Constant.AliyunSMSTemplateCode.LOGIN_FLAG;
import static com.twj.spirngbasics.server.util.Constant.Http.ERROR.*;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 用户登录
 */
@Service
@Slf4j
public class UserLoginService {

    public static final int TEMPLATE_CODE_LOGIN = 1;

    public static final int TEMPLATE_CODE_CHANGE = 2;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RabbitProducer rabbitProducer;

    @Resource
    private UserRegisterService userRegisterService;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SelectStatementProvider selectStatement = SqlBuilder.select(userMapper.selectList)
                .from(UserRoleUserDynamicSqlSupport.userRoleUser)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<UserRoleUserDto> userRoleUserDtoList = CopyUtils.copyList(userMapper.selectMany(selectStatement), UserRoleUserDto.class);
        pageDto.setTotal(userRoleUserDtoList.size());
        pageDto.setList(userRoleUserDtoList);
    }

    /**
     * 手机号+验证码
     *
     * @param phone 手机号
     * @param code  验证码
     */
    public ResponseDto phoneAndCode(String phone, String code) {
        ValidatorUtils.require(phone, LOGIN_PHONE_IS_NULL);
        ValidatorUtils.require(code, PHONE_CODE_IS_NULL);

        String val = RedisManage.getPhoneCode(LOGIN_FLAG + phone);

        //验证是否注册过，同时查询用户信息
        User user = userRegisterService.isRegister(phone);
        if (user == null) {
            throw new BusinessException(LOGIN_NOT_REGISTER);
        }

        //是否发送验证码
        if (StringUtils.isEmpty(val)) {
            throw new BusinessException(PHONE_NOT_SEND_CODE);
        }

        if (!val.equals(code)) {
            throw new BusinessException(PHONE_CODE_ERROR);
        }

        RedisManage.remove(LOGIN_FLAG + phone);

        if (user.getDele() == null || user.getDele().equals(BaseEntity.DELE_YES)) {
            throw new BusinessException(LOGIN_USER_DEL);
        }
        //todo 被禁用
//        if (user.) {
//            throw new BusinessException(LOGIN_USER_DISABLE);
//        }
        return loginSuccess(user);
    }

    /**
     * 手机号+密码
     *
     * @param phone    手机号
     * @param password 密码
     */
    public ResponseDto phoneAndPwd(String phone, String password) {
        ValidatorUtils.require(phone, LOGIN_PHONE_IS_NULL);
        ValidatorUtils.require(password, LOGIN_PWD_IS_NULL);
        //是否注册
        if (userRegisterService.isRegister(phone) == null) {
            throw new BusinessException(LOGIN_NOT_REGISTER);
        }

        //用户存在 登录验证
        SelectStatementProvider selectLogin = SqlBuilder.select(userMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.phone, SqlBuilder.isEqualTo(phone))
                .and(UserDynamicSqlSupport.pwd, SqlBuilder.isEqualTo(UserManage.pwdEncryption(password)))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<User> userligin = userMapper.selectMany(selectLogin);
        //登录失败-密码错误
        if (userligin == null || userligin.size() <= 0) {
            throw new BusinessException(LOGIN_USER_OR_PWD_ERROR);
        }
        User user = userligin.get(0);
        //登录成功
        return loginSuccess(user);
    }

    /**
     * 登录成功
     *
     * @param user
     */
    private ResponseDto loginSuccess(User user) {
        ResponseDto responseDto = ResponseDto.createBySuccess();
        String token = IdUtil.simpleUUID();
        responseDto.setToken(token);
//        userDto.setRequests(getUserRequestList(userDto.getId()));
        String newToken = EncryptUtils.encryptMD5ToString(token);
        System.out.println("token:" + token);
        System.out.println("newToken:" + newToken);
        RedisManage.setUserToken(newToken, user);
        UserDto userDto = CopyUtils.copy(user, UserDto.class);
        responseDto.setContent(userDto);
        return responseDto;
    }

    /**
     * 发送手机验证码
     *
     * @param phone        手机号
     * @param templateCode 短信模板
     */
    public void sendPhoneCode(String phone, int templateCode) {
        //验证是否是手机号
        if (phone.length() != 11) {
            throw new BusinessException(PHONE_NUMBER_ERROR);
        }
        //判断该用户是否注册过
        if (userRegisterService.isRegister(phone) == null) {
            throw new BusinessException(LOGIN_NOT_REGISTER);
        }
        String code = null;
        //判断手机号是否发送过验证码。
        if (templateCode == TEMPLATE_CODE_LOGIN) {
            code = RedisManage.getPhoneCode(LOGIN_FLAG + phone);
        } else if (templateCode == TEMPLATE_CODE_CHANGE) {
            code = RedisManage.getPhoneCode(CHANGE_FLAG + phone);
        }

        //  时效还未过期，请不要重复发送
        if (!StringUtils.isEmpty(code)) {
            throw new BusinessException(PHONE_SENDED_CODE);
        }

        // 向消息队列发送验证码请求
//        String ranCode = "888888";
        String ranCode = RandomUtil.randomNumbers(6);
        StringJoiner stringJoiner = new StringJoiner(",");
        if (templateCode == TEMPLATE_CODE_LOGIN) {
            stringJoiner.add(Constant.AliyunSMSTemplateCode.LOGIN);
        } else if (templateCode == TEMPLATE_CODE_CHANGE) {
            stringJoiner.add(Constant.AliyunSMSTemplateCode.CHANGE);
        }
        stringJoiner.add(phone);
        stringJoiner.add(ranCode);
        rabbitProducer.sendMsg(RabbitMqConfig.EXCHANGE_PROJECT,
                RabbitMqConfig.ROUTINGKEY_PHONE_MSG + RabbitMqConfig.QUETYPE_ALIYUN,
                stringJoiner.toString());
        if (templateCode == TEMPLATE_CODE_LOGIN) {
            RedisManage.setPhoneCode(LOGIN_FLAG + phone, ranCode);
        } else if (templateCode == TEMPLATE_CODE_CHANGE) {
            RedisManage.setPhoneCode(CHANGE_FLAG + phone, ranCode);
        }

    }

}
