package com.twj.spirngbasics.user.service;

import cn.hutool.core.util.RandomUtil;
import com.twj.spirngbasics.server.config.RabbitMqConfig;
import com.twj.spirngbasics.server.entity.User;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.manage.RedisManage;
import com.twj.spirngbasics.server.manage.UserManage;
import com.twj.spirngbasics.server.mq.RabbitProducer;
import com.twj.spirngbasics.server.util.Constant;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import com.twj.spirngbasics.user.mapper.UserDynamicSqlSupport;
import com.twj.spirngbasics.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.StringJoiner;

import static com.twj.spirngbasics.server.util.Constant.Http.ERROR.*;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 注册/修改密码
 */
@Service
@Slf4j
public class UserRegisterService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RabbitProducer rabbitProducer;

    @Resource
    private UserLoginService userLoginService;

    /**
     * 发送注册验证码
     *
     * @param phone
     */
    public void sendPhoneCode(String phone) {
        //验证是否是手机号
        if (phone.length() != 11) {
            throw new BusinessException(PHONE_NUMBER_ERROR);
        }
        //判断该用户是否注册
        if (isRegister(phone) != null) {
            throw new BusinessException(REGISTER_EXISTS);
        }
        //判断手机号是否发送过验证码。
        String code = RedisManage.getPhoneCode(phone);

        //  时效还未过期，请不要重复发送
        if (!StringUtils.isEmpty(code)) {
            throw new BusinessException(PHONE_SENDED_CODE);
        }

        // 向消息队列发送验证码请求
//        String ranCode = "888888";
        String ranCode = RandomUtil.randomNumbers(6);
        StringJoiner stringJoiner = new StringJoiner(",");
        stringJoiner.add(Constant.AliyunSMS.LOGIN);
        stringJoiner.add(phone);
        stringJoiner.add(ranCode);
        rabbitProducer.sendMsg(RabbitMqConfig.EXCHANGE_PROJECT,
                RabbitMqConfig.ROUTINGKEY_PHONE_MSG + RabbitMqConfig.QUETYPE_ALIYUN,
                stringJoiner.toString());
        RedisManage.setPhoneCode(phone, ranCode);
    }

    public User isRegister(String phone) {
        //是否注册
        SelectStatementProvider selectIsRegister = SqlBuilder.select(userMapper.selectList)
                .from(UserDynamicSqlSupport.user)
                .where(UserDynamicSqlSupport.phone, SqlBuilder.isEqualTo(phone))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<User> users = userMapper.selectMany(selectIsRegister);
        if (users == null || users.size() == 0) {
            return null;
        }
        return users.get(0);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    public void register(User user) {
        String code = user.getCode();
        String phone = user.getPhone();

        ValidatorUtils.require(phone, LOGIN_PHONE_IS_NULL);
        ValidatorUtils.require(code, PHONE_CODE_IS_NULL);

        //是否注册
        if (isRegister(phone) != null) {
            throw new BusinessException(REGISTER_EXISTS);
        }

        String pCode = RedisManage.getPhoneCode(phone);
        //是否发送验证码
        if (StringUtils.isEmpty(pCode)) {
            throw new BusinessException(PHONE_NOT_SEND_CODE);
        }

        //比对验证码
        if (!code.equals(pCode)) {
            throw new BusinessException(PHONE_CODE_ERROR);
        }


        //加密存储
        user.setPwd(UserManage.pwdEncryption(user.getPwd()));

        try {
            //添加数据库
//            User user1 = (CopyUtil.copy(userDto, User.class));
            user.insert("reg");

            userMapper.insert(user);
            //todo 创建用户与角色关联
//            RoleUser roleUser = new RoleUser();
//            roleUser.setId(UuidUtil.getShortUuid());
//            roleUser.setRoleId(user1.getRoleId());
//            roleUser.setUserId(user1.getId());
//            roleUserMapper.insert(roleUser);
        } catch (Exception e) {
            throw new BusinessException(REGISTER_FAIL);
        }

    }

    public void changePwdPhoneCode(String phone) {
        userLoginService.sendPhoneCode(phone);
    }

    public void changePwd(User user) {
        ValidatorUtils.require(user.getPhone(), "请填写手机号");
        ValidatorUtils.require(user.getPwd(), "请填写密码");
        ValidatorUtils.require(user.getCode(), "请填写验证码");

        String code = RedisManage.getPhoneCode(user.getPhone());
        if (StringUtils.isEmpty(code)) {
            throw new BusinessException(PHONE_NOT_SEND_CODE);
        }
        if (!code.equals(user.getCode())) {
            throw new BusinessException(PHONE_CODE_ERROR);
        }
        //是否注册
        User user1 = isRegister(user.getPhone());
        if (user1 == null) {
            throw new BusinessException(LOGIN_NOT_REGISTER);
        }
        user1.update("up");
        user1.setPwd(UserManage.pwdEncryption(user.getPwd()));
        userMapper.updateByPrimaryKeySelective(user1);
    }
}
