package com.twj.spirngbasics.business.controller.user;

import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.exception.ValidatorException;
import com.twj.spirngbasics.server.user.dto.UserRoleDto;
import com.twj.spirngbasics.server.user.entity.UserRole;
import com.twj.spirngbasics.server.user.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-05 17:27:50
 * @Version 1.0
 * @描述: 角色
 */
@RestController
@RequestMapping("${request.path.userRole}")
@Api(tags = "角色")
public class UserRoleController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRoleController.class);
    public static final String BUSINESS_NAME = "角色";

    @Resource
    private UserRoleService userRoleService;


    @ApiOperation("列表查询")
    @PostMapping("/list")
    public ResponseDto list(@RequestBody @Valid PageDto pageDto) {
        try {
            userRoleService.list(pageDto);
            return ResponseDto.createBySuccess(pageDto);
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    @ApiOperation("获取基础角色列表")
    @GetMapping("/basicRole")
    public ResponseDto basicRole() {
        ResponseDto responseDto = ResponseDto.createBySuccess();
        responseDto.setContent(userRoleService.basicRole());
        return responseDto;
    }

    @ApiOperation("保存，id有值时更新，无值时新增")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserRole userRole) {
        try {
            userRoleService.save(userRole);
            return ResponseDto.createBySuccess(userRole);
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
            userRoleService.delete(id);
            return ResponseDto.createBySuccess();
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    @ApiOperation("保存资源")
    @PostMapping("/save-resource")
    public ResponseDto saveResource(@RequestBody UserRoleDto roleDto) {
        LOG.info("保存角色资源关联开始");
        ResponseDto<UserRoleDto> responseDto = ResponseDto.createBySuccess();
        userRoleService.saveResource(roleDto);
        responseDto.setContent(roleDto);
        return responseDto;
    }

    @ApiOperation("加载该角色的资源树")
    @GetMapping("/list-resource/{roleId}")
    public ResponseDto listResource(@PathVariable String roleId) {
        LOG.info("加载资源开始");
        ResponseDto responseDto = ResponseDto.createBySuccess();
        List<String> resourceIdList = userRoleService.listResource(roleId);
        responseDto.setContent(resourceIdList);
        return responseDto;
    }

}
