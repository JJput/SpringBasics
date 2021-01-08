package com.twj.spirngbasics.business.controller.user;

import com.twj.spirngbasics.server.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("${request.path.user}")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    public static final String BUSINESS_NAME = "账号 ";

    @Resource
    private UserService userService;

//    /**
//     * 发送手机注册验证码
//     */
//    @RequestMapping(value = "phoneCodeRegister", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseDto<String> sendRegPhoneCode(String phone) {
//        return userService.sendPhoneCode(phone, UserService.SNED_PHONE_CODE_REGISTER);
//    }
//
//    /**
//     * 注册
//     */
//    @RequestMapping(value = "register", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseDto<String> register(@RequestBody UserDto user) {
//        return userService.register(user);
//    }
//
//    /**
//     * 发送手机登录验证码
//     */
//    @RequestMapping(value = "phoneCodeLogin", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseDto<String> sendLoginPhoneCode(String phone) {
//        return userService.sendPhoneCode(phone, UserService.SNED_PHONE_CODE_LOGIN);
//    }
//
//
//    /**
//     * 用户登录,通过手机号+密码
//     */
//    @RequestMapping(value = "loginPhoneAndPwd", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseDto<User> login(@RequestBody User user) {
//        //service->mybatis->dao
//        ResponseDto<User> response = userService.login(user.getPhone(), user.getPassword());
//        user.setPassword(null);
//        return response;
//    }
//
//    /**
//     * 用户登录,通过手机验证码
//     */
//    @RequestMapping(value = "loginPhoneAndCode", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseDto<User> loginPhone(@RequestBody UserDto user) {
//        //service->mybatis->dao
//        ResponseDto<User> response = userService.loginPhone(user.getPhone(), user.getCode());
//        return response;
//    }
//
//    /**
//     * 登出
//     */
//    @RequestMapping(value = "logout", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseDto<User> logout(@RequestHeader("token") String token) {
//        boolean sta = userService.logout(token);
//        if (sta) return ResponseDto.createBySuccess();
//        else return ResponseDto.createByFail(null);
//    }
//
//
//    /**
//     * 获取用户信息
//     *
//     * @return
//     */
//    @RequestMapping(value = "getuserinfo", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseDto<String> getUserInfo(@RequestHeader("token") String token) {
//        return userService.getUserInfo(token);
//    }

}
