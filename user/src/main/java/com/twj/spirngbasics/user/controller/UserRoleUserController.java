package com.twj.spirngbasics.user.controller;

import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.user.entity.UserRoleUser;
import com.twj.spirngbasics.user.service.UserRoleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 角色用户关联
 */
@RestController
@RequestMapping("/user/roleuser")
@Api(tags = "角色用户关联")
@Slf4j
public class UserRoleUserController {


    @Resource
    private UserRoleUserService userRoleUserService;

    @ApiOperation("查找对象")
    @GetMapping("/findId")
    public ResponseDto findById(String id) {
        return ResponseDto.createBySuccess(userRoleUserService.findById(id));
    }

    @ApiOperation("列表查询")
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        userRoleUserService.list(pageDto);
        return ResponseDto.createBySuccess(pageDto);
    }

    @ApiOperation("新增")
    @PostMapping("/insert")
    public ResponseDto create(@RequestBody @Valid UserRoleUser userRoleUser) {
        userRoleUserService.insert(userRoleUser);
        return ResponseDto.createBySuccess(userRoleUser);
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public ResponseDto update(@RequestBody UserRoleUser userRoleUser) {
        userRoleUserService.update(userRoleUser);
        return ResponseDto.createBySuccess();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        userRoleUserService.delete(id);
        return ResponseDto.createBySuccess();
    }
}
