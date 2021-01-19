package com.twj.spirngbasics.server.user.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.manage.RedisManage;
import com.twj.spirngbasics.server.manage.UserManage;
import com.twj.spirngbasics.server.user.dto.UserDto;
import com.twj.spirngbasics.server.user.dto.UserResourceDto;
import com.twj.spirngbasics.server.user.entity.User;
import com.twj.spirngbasics.server.user.entity.UserExample;
import com.twj.spirngbasics.server.user.entity.UserRole;
import com.twj.spirngbasics.server.user.entity.UserRoleUser;
import com.twj.spirngbasics.server.user.mapper.UserMapper;
import com.twj.spirngbasics.server.user.mapper.UserRoleUserMapper;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.EncryptUtils;
import com.twj.spirngbasics.server.util.PhoneCodeCache;
import com.twj.spirngbasics.server.util.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.twj.spirngbasics.server.util.Constant.Http.ERROR.*;

@Service
@Slf4j
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleUserMapper userRoleUserMapper;

    /**
     * 登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return
     */
    public ResponseDto login(String phone, String password) {
        if (StringUtils.isEmpty(phone))
            return ResponseDto.createByFail(LOGIN_USER_OR_PWD_ERROR);


        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(phone);
        if (userMapper.countByExample(example) <= 0) {
            return ResponseDto.createByFail(LOGIN_NOT_REGISTER);   //未注册
        }

        //用户存在
        UserExample userExample = new UserExample();
        userExample
                .createCriteria()
                .andPhoneEqualTo(phone)
                .andPasswordEqualTo(UserManage.pwdEncryption(password));
        List<User> userList = userMapper.selectByExample(userExample);
        //登录失败-密码错误
        if (userList == null || userList.size() <= 0) {
            return ResponseDto.createByFail(LOGIN_USER_OR_PWD_ERROR);
        }
        User user = userList.get(0);
        user.clear();
        //登录成功
        return ResponseDto.createBySuccess(loginSuccess(user));
    }


    public ResponseDto<User> loginPhone(String phone, String code) {
        if (StringUtils.isEmpty(phone))
            return ResponseDto.createByFail(PHONE_NUMBER_ERROR);


        String val = PhoneCodeCache.getValue(PhoneCodeCache.LOGIN + phone);
        //是否发送验证码
        if (StringUtils.isEmpty(val))
            return ResponseDto.createByFail(PHONE_NOT_SEND_CODE);

        if (!val.equals(code)) {
            return ResponseDto.createByFail(PHONE_CODE_ERROR);
        }
        //查询用户信息
        UserExample example = new UserExample();
        example.createCriteria().andPhoneEqualTo(phone);

        User user = userMapper.selectByExample(example).get(0);
        if (user != null) user.clear();
        return ResponseDto.createBySuccess(loginSuccess(user));
    }

    /**
     * 登录成功
     *
     * @param user
     */
    private UserDto loginSuccess(User user) {
        UserDto userDto = CopyUtils.copy(user, UserDto.class);
        String token = UuidUtils.getUuid8();
        userDto.setToken(token);
        userDto.setRequests(getUserRequestList(userDto.getId()));
        String newToken = EncryptUtils.encryptMD5ToString(token);
        log.info("token:" + token);
        log.info("newToken:" + newToken);
        RedisManage.setUserToken(newToken, JSON.toJSONString(userDto));
        userDto.setRequests(null);
        return userDto;
    }

    /**
     * 设置该用户的权限列表
     *
     * @param id
     * @return
     */
    private HashSet getUserRequestList(String id) {
        List<UserResourceDto> resourceDtoList = userMapper.findResources(id);
        // 整理所有有权限的请求，用于接口拦截
        HashSet<String> requestSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(resourceDtoList)) {
            for (int i = 0, l = resourceDtoList.size(); i < l; i++) {
                UserResourceDto resourceDto = resourceDtoList.get(i);
                String request = resourceDto.getRequest();
                if (!StringUtils.isEmpty(request))
                    requestSet.add(request);
//                String arrayString = resourceDto.getRequest();
//                List<String> requestList = JSON.parseArray(arrayString, String.class);
//                if (!CollectionUtils.isEmpty(requestList)) {
//                    requestSet.addAll(requestList);
//                }
            }
        }
        return requestSet;
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    public ResponseDto<String> register(UserDto user) {
        String code = user.getCode();
        String phone = user.getPhone();
//        String email = user.getEmail();

        if (StringUtils.isEmpty(phone))
            return ResponseDto.createByFail(PHONE_NUMBER_ERROR);
        if (StringUtils.isEmpty(code))
            return ResponseDto.createByFail(PHONE_CODE_IS_NULL);

        String pCode = PhoneCodeCache.getValue(PhoneCodeCache.REGISTER + phone);
        //是否发送验证码
        if (StringUtils.isEmpty(pCode))
            return ResponseDto.createByFail(PHONE_NOT_SEND_CODE);

        //比对验证码
        if (!code.equals(pCode))
            return ResponseDto.createByFail(PHONE_CODE_ERROR);

        //判断手机号是否正确
        if (phone.length() != 11)//Pattern.compile(REGEX_PHONE, Pattern.CASE_INSENSITIVE).matcher(phone).matches())
            return ResponseDto.createByFail(PHONE_NUMBER_ERROR);
        //查询该手机号是否被注册
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        if (userMapper.countByExample(userExample) > 0)
            return ResponseDto.createByFail(REGISTER_EXISTS);


        //加密存储
        user.setPassword(UserManage.pwdEncryption(user.getPassword()));

        try {
            //添加数据库
            User user1 = (CopyUtils.copy(user, User.class));
            user1.insert("1");
            userMapper.insert(user1);
            //创建用户与角色关联
            UserRoleUser roleUser = new UserRoleUser();
            roleUser.setId(UuidUtils.getUuid8());
            roleUser.setRoleId(user1.getRoleId());
            roleUser.setUserId(user1.getId());
            userRoleUserMapper.insert(roleUser);
            return ResponseDto.createBySuccess();
        } catch (Exception e) {
            return ResponseDto.createByFail(REGISTER_FAIL);
        }


    }


    /**
     * 发送手机验证码
     *
     * @param phone
     * @return
     */
    public ResponseDto<String> sendPhoneCode(String phone, int type) {
        String code = null;
        //验证是否是手机号
        if (phone.length() != 11) {
            return ResponseDto.createByFail(PHONE_NUMBER_ERROR);
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo(phone);
        //判断手机号是否在1分钟内发送过验证码。
//        if (type == SNED_PHONE_CODE_LOGIN) {
//            //登录验证码 - 手机号有注册的情况下进行
//            if (userMapper.countByExample(userExample) <= 0)
//                return ResponseDto.createByFail(REGISTERED_PHONE_NOT);
//            code = PhoneCodeCache.getValue(PhoneCodeCache.LOGIN + phone);
//        } else if (type == SNED_PHONE_CODE_REGISTER) {
//            code = PhoneCodeCache.getValue(PhoneCodeCache.REGISTER + phone);
//            //注册验证码 - 手机号未注册的情况下进行
//            if (userMapper.countByExample(userExample) > 0)
//                return ResponseDto.createByFail(REGISTERED_PHONE_OCCUPY);
//        } else if (type == SNED_PHONE_CODE_FORGET) {
//            //修改/忘记密码  验证码 - 手机号有注册的情况下进行
//            if (userMapper.countByExample(userExample) <= 0)
//                return ResponseDto.createByFail(REGISTERED_PHONE_NOT);
//            code = PhoneCodeCache.getValue(PhoneCodeCache.FORGET + phone);
//        }
        //  时效还未过期，请不要重复发送
//        if (!StringUtils.isEmpty(code))
//            return ResponseDto.createByFail(DO_NOT_REPEAT);
//
//        // 向消息队列发送验证码请求
//        String ranCode = "888888"; //getrandom();
//        if (type == SNED_PHONE_CODE_LOGIN) {
//            rabbitmqTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.TOPIC, getSendMsg(
//                    SIGN_NAME,
//                    TEMPLATE_CODE_LOGIN,
//                    ranCode,
//                    phone
//            ));
//            PhoneCodeCache.setKey(PhoneCodeCache.LOGIN + phone, ranCode);
//        } else if (type == SNED_PHONE_CODE_REGISTER) {
//            rabbitmqTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, RabbitMqConfig.TOPIC, getSendMsg(
//                    SIGN_NAME,
//                    TEMPLATE_CODE_REGISTER,
//                    ranCode,
//                    phone
//            ));
//            PhoneCodeCache.setKey(PhoneCodeCache.REGISTER + phone, ranCode);
//        } else if (type == SNED_PHONE_CODE_FORGET) {
//            PhoneCodeCache.setKey(PhoneCodeCache.FORGET + phone, ranCode);
//        }
        return ResponseDto.createBySuccess();
    }


    public ResponseDto<String> getUserInfo(String token) {
        if (StringUtils.isEmpty(token))
            return ResponseDto.createByFail("token为空");
        Object object = RedisManage.getUserToken(token);
        if (ObjectUtils.isEmpty(object))
            return ResponseDto.createByFail("该用户未登录");
        JSONObject loginUserDto = JSON.parseObject(String.valueOf(object));
        String id = loginUserDto.getString("id");
        if (StringUtils.isEmpty(id))
            return ResponseDto.createByFail("错误的用户,id为空");
        UserRole role = userMapper.selectRole(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", loginUserDto);
        jsonObject.put("role", role);
        return ResponseDto.createBySuccess(jsonObject);
    }


    /**
     * 更新
     */
    private void update(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 根据公司id获取用户列表
     *
     * @return
     */
    public ResponseDto list(PageDto pageDto) {
        try {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            List<Map> userList = userMapper.selectListAndRoleId(pageDto.getList().get(0).toString());
            PageInfo<Map> pageInfo = new PageInfo<>(userList);
            pageDto.setTotal(pageInfo.getTotal());
            pageDto.setList(userList);
        } catch (Exception e) {
            return ResponseDto.createByFail("no companyId");
        }
        return ResponseDto.createBySuccess(pageDto);
    }

    public boolean logout(String token) {
        RedisManage.remove(token);
        return true;
    }

    private String getrandom() {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int r = random.nextInt(10); //每次随机出一个数字（0-9）
            code = code + r;  //把每次随机出的数字拼在一起
        }
        return code;
    }

    /**
     * 生成发送模板
     *
     * @param signName     短信签名名称
     * @param templateCode 短信模板ID
     * @param code         验证码
     * @param phone        手机号
     * @return
     */
    private String getSendMsg(final String signName, final String templateCode, final String code, final String phone) {
        StringBuffer buf = new StringBuffer();
        buf.append(signName);
        buf.append(",");
        buf.append(templateCode);
        buf.append(",");
        buf.append(code);
        buf.append(",");
        buf.append(phone);
        return buf.toString();
    }

}
