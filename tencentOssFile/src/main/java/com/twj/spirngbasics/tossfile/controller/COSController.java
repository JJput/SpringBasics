package com.twj.spirngbasics.tossfile.controller;



import com.twj.spirngbasics.tossfile.entity.CosKey;
import com.twj.spirngbasics.tossfile.entity.ResponseDto;
import com.twj.spirngbasics.tossfile.service.COSService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @作者: JJ
 * @创建时间: 2020/8/17 下午3:37
 * @Version 1.0
 * @描述:
 */
@RestController
@RequestMapping("/oss")
public class COSController {

    @Resource
    private COSService cosService;


    @PostMapping("/del")
    public ResponseDto del(@RequestBody List<Map<String,Object>> Data) {
        List<String> names = new ArrayList<>();
        for (Map<String,Object> map : Data) {
            names.add((String) map.get("path"));
        }
        cosService.delObjects(names);
        return ResponseDto.createBySuccess("");
    }

    /**
     * 保存，id有值时更新，无值时新增
     *
     * @param cid 公司id
     * @param tid 账套id
     * @return
     */
    @GetMapping("/list")
    public ResponseDto list(String cid, String tid) {
        List<Map<String, Object>> data = cosService.getRootDirectory("/" + cid + "/" + tid + "/");
        ResponseDto responseDto = ResponseDto.createBySuccess(data);
        return responseDto;
    }

    /**
     * 创建上传密钥，时效10分钟
     *
     * @param cid 用户id
     * @param tid 账套id
     * @return
     */
    @GetMapping("/createPushKey")
    public ResponseDto createPushKey(String cid, String tid) {
        CosKey cosKey = cosService.createKey("/" + cid + "/" + tid + "/*", false);
        return ResponseDto.createBySuccess(cosKey);
    }


    /**
     * 创建下载密钥，时效10分钟
     *
     * @param path 下载文件路径
     * @return
     */
    @GetMapping("/createPullKey")
    public ResponseDto createPullKey(String path) {
        CosKey cosKey = cosService.createKey(path, true);
        return ResponseDto.createBySuccess(cosKey);
    }
}
