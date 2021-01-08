package com.twj.spirngbasics.business.controller;


import com.twj.spirngbasics.server.dto.ResponseDto;
import com.twj.spirngbasics.server.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "${request.path.test}")
public class SysLogController {

    private static final Logger LOG = LoggerFactory.getLogger(SysLogController.class);
    public static final String BUSINESS_NAME = "";

    @Resource
    private SysLogService sysLogService;

    /**
     * 列表查询
     */
    @GetMapping("/test")
    public ResponseDto list() {
        return ResponseDto.createBySuccess();
    }

}
