package com.twj.spirngbasics.business.controller.user;

import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.exception.ValidatorException;
import com.twj.spirngbasics.server.user.entity.UserRoleResource;
import com.twj.spirngbasics.server.user.service.UserRoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @作者: Jun
 * @创建时间: 2021-01-05 17:27:51
 * @Version 1.0
 * @描述: 角色资源关联
 */
@RestController
@RequestMapping("${request.path.userRoleResource}")
@Api(tags = "角色资源关联")
@Slf4j
public class UserRoleResourceController {

    @Resource
    private UserRoleResourceService userRoleResourceService;


    @ApiOperation("列表查询")
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        try {
            userRoleResourceService.list(pageDto);
            return ResponseDto.createBySuccess(pageDto);
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    @ApiOperation("保存，id有值时更新，无值时新增")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserRoleResource userRoleResource) {
        try {
            userRoleResourceService.save(userRoleResource);
            return ResponseDto.createBySuccess(userRoleResource);
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }


    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        try {
            userRoleResourceService.delete(id);
            return ResponseDto.createBySuccess();
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }
}
