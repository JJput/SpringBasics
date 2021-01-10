package com.twj.spirngbasics.business.controller.user;

import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.exception.ValidatorException;
import com.twj.spirngbasics.server.user.entity.UserRoleUser;
import com.twj.spirngbasics.server.user.service.UserRoleUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @作者: Jun
 * @创建时间: 2021-01-05 17:27:51
 * @Version 1.0
 * @描述: 角色用户关联
 */
@RestController
@RequestMapping("${request.path.userRoleUser}")
@Api(tags = "角色用户关联")
public class UserRoleUserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRoleUserController.class);
    public static final String BUSINESS_NAME = "角色用户关联";

    @Resource
    private UserRoleUserService userRoleUserService;


    @ApiOperation("列表查询")
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        try {
            userRoleUserService.list(pageDto);
            return ResponseDto.createBySuccess(pageDto);
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    @ApiOperation("保存，id有值时更新，无值时新增")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody UserRoleUser userRoleUser) {
        try {
            userRoleUserService.save(userRoleUser);
            return ResponseDto.createBySuccess(userRoleUser);
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
            userRoleUserService.delete(id);
            return ResponseDto.createBySuccess();
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }
}
