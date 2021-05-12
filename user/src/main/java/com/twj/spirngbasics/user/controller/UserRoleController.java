package com.twj.spirngbasics.user.controller;

import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.manage.RedisManage;
import com.twj.spirngbasics.user.dto.UserResourceDto;
import com.twj.spirngbasics.user.dto.UserRoleDto;
import com.twj.spirngbasics.user.entity.UserRole;
import com.twj.spirngbasics.user.service.UserResourceService;
import com.twj.spirngbasics.user.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 角色
 */
@RestController
@RequestMapping("/user/role")
@Api(tags = "角色")
@Slf4j
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @Resource
    private UserResourceService userResourceService;

    @ApiOperation("列表查询")
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        userRoleService.list(pageDto);
        return ResponseDto.createBySuccess(pageDto);
    }

    @ApiOperation("新增")
    @PostMapping("/insert")
    public ResponseDto create(@RequestBody @Valid UserRole userRole) {
        userRoleService.insert(userRole);
        RedisManage.setRoleResource(userRole.getId(), userRoleService.getUserResourceList(userRole.getId()));
        return ResponseDto.createBySuccess(userRole);
    }

    @ApiOperation("新增-资源树")
    @GetMapping("/insert-resource-tree")
    public ResponseDto loadTree() {
        List<UserResourceDto> resourceDtoList = userResourceService.loadTree(true);
        return ResponseDto.createBySuccess(resourceDtoList);
    }

    @ApiOperation("加载该角色的资源树")
    @GetMapping("/insert-list-resource")
    public ResponseDto listResource(String roleId) {
        List<String> resourceIdList = userRoleService.listResource(roleId);
        return ResponseDto.createBySuccess(resourceIdList);
    }
    
    @ApiOperation("更新")
    @PostMapping("/insert-update")
    public ResponseDto update(@RequestBody @Valid UserRole userRole) {
        userRoleService.update(userRole);
        RedisManage.setRoleResource(userRole.getId(), userRoleService.getUserResourceList(userRole.getId()));
        return ResponseDto.createBySuccess();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        userRoleService.delete(id);
        return ResponseDto.createBySuccess();
    }

}
