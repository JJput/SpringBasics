package ${PACKAGE_CONTROLLER};

import lombok.extern.slf4j.Slf4j;
import ${PACKAGE_ENTITY}.${Domain};
import ${PACKAGE_DTO}.${Domain}Dto;
import ${PACKAGE_SERVER}.dto.PageDto;
import ${PACKAGE_SERVER}.dto.ResponseDto;
import ${PACKAGE_SERVICE}.${Domain}Service;
import ${PACKAGE_SERVER}.util.CopyUtils;
import ${PACKAGE_SERVER}.exception.BusinessException;
import ${PACKAGE_SERVER}.exception.ValidatorException;
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
@Slf4j
public class ${Domain}Controller {

<#--    public static final String BUSINESS_NAME = "${tableNameCn}";-->

    @Resource
    private ${Domain}Service ${domain}Service;

    @ApiOperation("查询对象")
    @ApiOperationSupport(order = 1)
    @GetMapping("/findId")
    public ResponseDto findById(String id) {
        return ResponseDto.createBySuccess(${domain}Service.findById(id));
    }

    @ApiOperation("查询列表")
    @ApiOperationSupport(order = 2)
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ${domain}Service.list(pageDto);
        return ResponseDto.createBySuccess(pageDto);
    }

    @ApiOperation("新增")
    @ApiOperationSupport(order = 3)
    @PostMapping("/insert")
    public ResponseDto create(@RequestBody @Valid ${Domain} ${domain}) {
        ${domain}Service.insert(${domain});
        return ResponseDto.createBySuccess(${domain});
    }

    @ApiOperation("更新")
    @ApiOperationSupport(order = 4)
    @PostMapping("/update")
    public ResponseDto update(@RequestBody ${Domain} ${domain}) {
        ${domain}Service.update(${domain});
        return ResponseDto.createBySuccess();
    }

    @ApiOperation("删除")
    @ApiOperationSupport(order = 5)
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ${domain}Service.delete(id);
        return ResponseDto.createBySuccess();
    }
}
