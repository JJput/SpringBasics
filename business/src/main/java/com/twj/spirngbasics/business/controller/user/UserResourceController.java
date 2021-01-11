package com.twj.spirngbasics.business.controller.user;

import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.exception.ValidatorException;
import com.twj.spirngbasics.server.user.dto.UserResourceDto;
import com.twj.spirngbasics.server.user.service.UserResourceService;
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
 * @创建时间: 2021-01-05 17:27:49
 * @Version 1.0
 * @描述: 资源
 */
@RestController
@RequestMapping("${request.path.userResource}")
@Api(tags = "资源",description = "资源树，所有请求路径")
public class UserResourceController {

    private static final Logger LOG = LoggerFactory.getLogger(UserResourceController.class);
    public static final String BUSINESS_NAME = "资源";

    @Resource
    private UserResourceService userResourceService;

    @ApiOperation("列表查询")
    @PostMapping("/list")
    public ResponseDto list(@RequestBody @Valid PageDto pageDto) {
        try {
            userResourceService.list(pageDto);
            return ResponseDto.createBySuccess(pageDto);
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    @ApiOperation("保存，id有值时更新，无值时新增")
    @PostMapping("/save")
    public ResponseDto save(@RequestBody List<UserResourceDto> userResourceDtoList) {
        try {
            userResourceService.saveJson(userResourceDtoList);
            return ResponseDto.createBySuccess();
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        try {
            userResourceService.delete(id);
            return ResponseDto.createBySuccess();
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    @ApiOperation("资源树查询")
    @GetMapping("/load-tree")
    public ResponseDto loadTree() {
        try {
            ResponseDto responseDto = ResponseDto.createBySuccess();
            List<UserResourceDto> resourceDtoList = userResourceService.loadTree();
            responseDto.setContent(resourceDtoList);
            return ResponseDto.createBySuccess(responseDto);
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }
}
