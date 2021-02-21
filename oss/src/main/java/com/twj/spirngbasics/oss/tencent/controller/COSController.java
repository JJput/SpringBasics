package com.twj.spirngbasics.oss.tencent.controller;


import com.twj.spirngbasics.oss.tencent.entity.CosKey;
import com.twj.spirngbasics.oss.tencent.service.COSService;
import com.twj.spirngbasics.server.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @作者: JJ
 * @创建时间: 2020/8/17 下午3:37
 * @Version 1.0
 * @描述: 腾讯对象存储
 */
@RestController
@RequestMapping("/oss")
@Api(tags = "对象存储-腾讯云 ")
@Slf4j
public class COSController {

    @Resource
    private COSService cosService;


    @ApiOperation("删除")
    @PostMapping("/del")
    public ResponseDto del(@RequestBody List<Map<String, Object>> Data) {
        List<String> names = new ArrayList<>();
        for (Map<String, Object> map : Data) {
            names.add((String) map.get("path"));
        }
        cosService.delObjects(names);
        return ResponseDto.createBySuccess("");
    }


    @ApiOperation("查看指定路径下所有文件")
    @GetMapping("/list")
    public ResponseDto list(String path) {
        List<Map<String, Object>> data = cosService.getRootDirectory(path);
        ResponseDto responseDto = ResponseDto.createBySuccess(data);
        return responseDto;
    }

    @ApiOperation("创建上传密钥")
    @GetMapping("/createPushKey")
    public ResponseDto createPushKey(String path) {
        CosKey cosKey = cosService.createKey(path, false);
        return ResponseDto.createBySuccess(cosKey);
    }


    @ApiOperation("创建下载密钥")
    @GetMapping("/createPullKey")
    public ResponseDto createPullKey(String path) {
        CosKey cosKey = cosService.createKey(path, true);
        return ResponseDto.createBySuccess(cosKey);
    }
}
