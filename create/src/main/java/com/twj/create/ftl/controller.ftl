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

import javax.annotation.Resource;

/**
 * @作者: ${AUTHOR}
 * @创建时间: ${TIME}
 * @Version 1.0
 * @描述: ${tableNameCn}
 */
@RestController
@RequestMapping("${'$'}{request.path.${domain}}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    public static final String BUSINESS_NAME = "${tableNameCn}";

    @Resource
    private ${Domain}Service ${domain}Service;

    @GetMapping("/findId")
    public ResponseDto findById(String id) {
        try {
            return ResponseDto.createBySuccess(
                CopyUtils.copy(${domain}Service.findById(id), ${Domain}Dto.class));
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    /**
     * 列表查询
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        try {
            ${domain}Service.list(pageDto);
            return ResponseDto.createBySuccess(pageDto);
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ${Domain} ${domain}) {
        try {
            ${domain}Service.save(${domain});
            return ResponseDto.createBySuccess(${domain});
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        try {
            ${domain}Service.delete(id);
            return ResponseDto.createBySuccess();
        } catch (BusinessException | ValidatorException e) {
            return ResponseDto.createByFail(e);
        } catch (Exception e) {
            return ResponseDto.createByFail(e.getMessage());
        }
    }
}
