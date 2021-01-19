package com.twj.spirngbasics.user.controller;

import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.user.entity.UserRoleResource;
import com.twj.spirngbasics.user.service.UserRoleResourceService;
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
 * @描述: 角色资源关联
 */
@RestController
@RequestMapping("/user/roleresource")
@Api(tags = "角色资源关联")
@Slf4j
public class UserRoleResourceController {


    @Resource
    private UserRoleResourceService userRoleResourceService;


    @ApiOperation("列表查询")
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        userRoleResourceService.list(pageDto);
        return ResponseDto.createBySuccess(pageDto);
    }

    @ApiOperation("新增")
    @PostMapping("/insert")
    public ResponseDto create(@RequestBody @Valid UserRoleResource userRoleResource) {
        userRoleResourceService.insert(userRoleResource);
        return ResponseDto.createBySuccess(userRoleResource);
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public ResponseDto update(@RequestBody UserRoleResource userRoleResource) {
        userRoleResourceService.update(userRoleResource);
        return ResponseDto.createBySuccess();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        userRoleResourceService.delete(id);
        return ResponseDto.createBySuccess();
    }
}
