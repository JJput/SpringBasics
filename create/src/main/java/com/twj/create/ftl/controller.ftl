package ${PACKAGE_CONTROLLER};

import ${PACKAGE_ENTITY}.${Domain};
import ${PACKAGE_DTO}.${Domain}Dto;
import ${PACKAGE_DTO}.PageDto;
import ${PACKAGE_DTO}.ResponseDto;
import ${PACKAGE_SERVICE}.${Domain}Service;
import ${PACKAGE_UTILS}.CopyUtils;
import ${PACKAGE_EXCEPTION}.BusinessException;
import ${PACKAGE_EXCEPTION}.ValidatorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import javax.annotation.Resource;

/**
 * @作者: ${AUTHOR}
 * @创建时间: ${TIME}
 * @Version 1.0
 * @描述: ${tableNameCn}
 */
@RestController
@RequestMapping("${'$'}{request.path.${domain}}")
@Api(tags = "${tableNameCn}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
<#--    public static final String BUSINESS_NAME = "${tableNameCn}";-->

    @Resource
    private ${Domain}Service ${domain}Service;

    @ApiOperation("查找对象")
    @GetMapping("/findId")
    public ResponseDto findById(String id) {
        return ResponseDto.createBySuccess(${domain}Service.findById(id));
    }

    @ApiOperation("列表查询")
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ${domain}Service.list(pageDto);
        return ResponseDto.createBySuccess(pageDto);
    }

    @ApiOperation("新增")
    @PostMapping("/insert")
    public ResponseDto create(@RequestBody @Valid ${Domain} ${domain}) {
        ${domain}Service.insert(${domain});
        return ResponseDto.createBySuccess(${domain});
    }

    @ApiOperation("更新")
    @PostMapping("/update")
    public ResponseDto update(@RequestBody ${Domain} ${domain}) {
        ${domain}Service.update(${domain});
        return ResponseDto.createBySuccess();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ${domain}Service.delete(id);
        return ResponseDto.createBySuccess();
    }
}
