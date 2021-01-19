package com.twj.spirngbasics.business.controller;


import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.entity.SysLog;
import com.twj.spirngbasics.server.exception.BusinessException;
import com.twj.spirngbasics.server.exception.ValidatorException;
import com.twj.spirngbasics.server.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "${request.path.syslog}")
@Api(tags = "系统日志")
@Slf4j
public class SysLogController {

    @Resource
    private SysLogService sysLogService;

    /**
     * 列表查询
     */
    @PostMapping("/list")
    @ApiOperation("日志列表")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        try {
            List<SysLog> list = sysLogService.list(pageDto.getPage(), pageDto.getSize());
            pageDto.setList(list);
            return ResponseDto.createBySuccess(pageDto);
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

}
