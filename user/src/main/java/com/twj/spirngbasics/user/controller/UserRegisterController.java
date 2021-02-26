package com.twj.spirngbasics.user.controller;

import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.entity.User;
import com.twj.spirngbasics.user.dto.UserDto;
import com.twj.spirngbasics.user.service.UserRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author jjput
 * @Date 2021-02-24 17:10:48
 * @Version 1.0
 * @Describe 登录相关
 */
@RestController
@RequestMapping("/user/register")
@Api(tags = "用户注册或修改")
@Slf4j
public class UserRegisterController {

    @Resource
    private UserRegisterService userRegisterService;


    @ApiOperation("发送注册手机验证码")
    @PostMapping("/sendPhoneCode")
    public ResponseDto sendPhoneCode(@RequestBody UserDto userDto) {
        userRegisterService.sendPhoneCode(userDto.getPhone());
        return ResponseDto.createBySuccess();
    }

    @ApiOperation("注册")
    @PostMapping("/reg")
    public ResponseDto register(@RequestBody @Valid User user) {
        userRegisterService.register(user);
        return ResponseDto.createBySuccess();
    }

    @ApiOperation("发送修改密码-手机验证码")
    @PostMapping("/changePwdPhoneCode")
    public ResponseDto changePwdPhoneCode(@RequestBody UserDto userDto) {
        userRegisterService.changePwdPhoneCode(userDto.getPhone());
        return ResponseDto.createBySuccess();
    }

    @ApiOperation("修改密码")
    @PostMapping("/changePwd")
    public ResponseDto changePwd(@RequestBody User user) {
        userRegisterService.changePwd(user);
        return ResponseDto.createBySuccess();
    }


}
