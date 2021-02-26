package com.twj.spirngbasics.user.controller;

import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.entity.User;
import com.twj.spirngbasics.server.manage.UserManage;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.user.dto.UserDto;
import com.twj.spirngbasics.user.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author jjput
 * @Date 2021-02-24 17:10:48
 * @Version 1.0
 * @Describe 登录相关
 */
@RestController
@RequestMapping("/user/login")
@Api(tags = "用户登录")
@Slf4j
public class UserLoginController {

    @Resource
    private UserLoginService userLoginService;


    @ApiOperation("发送登录手机验证码")
    @PostMapping("/sendPhoneCode")
    public ResponseDto sendPhoneCode(@RequestBody UserDto userDto) {
        userLoginService.sendPhoneCode(userDto.getPhone(), UserLoginService.TEMPLATE_CODE_LOGIN);
        return ResponseDto.createBySuccess();
    }

    @ApiOperation("手机号+验证码")
    @PostMapping("/phoneAndCode")
    public ResponseDto phoneAndCode(@RequestBody UserDto userDto) {
        return userLoginService.phoneAndCode(userDto.getPhone(), userDto.getCode());

    }

    @ApiOperation("手机号+密码")
    @PostMapping("/phoneAndPwd")
    public ResponseDto phoneAndPwd(@RequestBody User user) {
        return userLoginService.phoneAndPwd(user.getPhone(), user.getPwd());
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public ResponseDto getUserInfo() {
        return ResponseDto.createBySuccess(
                CopyUtils.copy(UserManage.getUser(), UserDto.class)
        );
    }

}
