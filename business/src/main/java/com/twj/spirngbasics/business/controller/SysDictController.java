package com.twj.spirngbasics.business.controller;

import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.dto.SysDictDto;
import com.twj.spirngbasics.server.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 15:51:40
 * @Version 1.0
 * @描述: 字典表 
 */
@RestController
@RequestMapping("${request.path.sysDict}")
@Api(tags = "字典表 ")
@Slf4j
public class SysDictController {


    @Resource
    private SysDictService sysDictService;

    @ApiOperation("查找对象")
    @GetMapping("/findId")
    public ResponseDto findById(String id) {
        return ResponseDto.createBySuccess(sysDictService.findById(id));
    }

    @ApiOperation("列表查询")
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto<SysDictDto> pageDto) {
        sysDictService.list(pageDto);
        return ResponseDto.createBySuccess(pageDto);
    }
}
