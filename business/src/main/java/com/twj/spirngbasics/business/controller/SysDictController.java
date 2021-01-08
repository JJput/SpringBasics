package com.twj.spirngbasics.business.controller;

import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.dto.SysDictDto;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.exception.ValidatorException;
import com.twj.spirngbasics.server.service.SysDictService;
import com.twj.spirngbasics.server.util.CopyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @作者: Jun
 * @创建时间: 2021-01-06 15:35:31
 * @Version 1.0
 * @描述: 字典表
 */
@RestController
@RequestMapping("${request.path.sysDict}")
public class SysDictController {

    private static final Logger LOG = LoggerFactory.getLogger(SysDictController.class);
    public static final String BUSINESS_NAME = "字典表 ";

    @Resource
    private SysDictService sysDictService;

    @GetMapping("/findId")
    public ResponseDto findById(String id) {
        try {
            return ResponseDto.createBySuccess(
                    CopyUtils.copy(sysDictService.findById(id), SysDictDto.class));
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    /**
     * 列表查询
     * 查询请求示例
     * {
     * 	"page": 0,
     * 	"size": 0,
     * 	"total": 0,
     * 	"data": {
     * 	    "type":"age"
     * 	}
     * }
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto<SysDictDto> pageDto) {
        try {
            sysDictService.list(pageDto);
            return ResponseDto.createBySuccess(pageDto);
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

}
