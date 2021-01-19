package com.twj.spirngbasics.user.controller;

import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.user.dto.UserResourceDto;
import com.twj.spirngbasics.user.service.UserResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:37
 * @Version 1.0
 * @描述: 资源
 */
@RestController
@RequestMapping("/user/resource")
@Api(tags = "资源")
@Slf4j
public class UserResourceController {

    @Resource
    private UserResourceService userResourceService;

    @ApiOperation("资源树查询")
    @GetMapping("/load-tree")
    public ResponseDto loadTree() {
        List<UserResourceDto> resourceDtoList = userResourceService.loadTree();
        return ResponseDto.createBySuccess(resourceDtoList);
    }


}
