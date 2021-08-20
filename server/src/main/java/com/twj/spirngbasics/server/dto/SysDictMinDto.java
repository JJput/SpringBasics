package com.twj.spirngbasics.server.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;


/**
 * @作者: Jun
 * @创建时间: 2021-01-21 15:56:06
 * @Version 1.0
 * @描述: 字典表 
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDictMinDto {

    @ApiModelProperty(value = "数据值", required = true)
    @NotEmpty(message = "value不能为空")
    @Length(min = 0, max = 128, message = "value长度异常,取值范围0~128")
    private String value;

    @ApiModelProperty(value = "标签名", required = true)
    @NotEmpty(message = "label不能为空")
    @Length(min = 0, max = 128, message = "label长度异常,取值范围0~128")
    private String label;

}
