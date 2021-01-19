package com.twj.spirngbasics.server.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;


/**
 * @作者: Jun
 * @创建时间: 2021-01-19 15:51:40
 * @Version 1.0
 * @描述: 字典表 
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDictDto {

    @ApiModelProperty(value = "编号", required = true)
    @NotEmpty(message = "id不能为空")
    @Length(min = 0, max = 32, message = "id长度异常,取值范围0~32")
    private String id;

    @ApiModelProperty(value = "数据值", required = true)
    @NotEmpty(message = "value不能为空")
    @Length(min = 0, max = 128, message = "value长度异常,取值范围0~128")
    private String value;

    @ApiModelProperty(value = "标签名", required = true)
    @NotEmpty(message = "label不能为空")
    @Length(min = 0, max = 128, message = "label长度异常,取值范围0~128")
    private String label;

    @ApiModelProperty(value = "类型", required = true)
    @NotEmpty(message = "type不能为空")
    @Length(min = 0, max = 128, message = "type长度异常,取值范围0~128")
    private String type;

    @ApiModelProperty(value = "描述", required = true)
    @NotEmpty(message = "description不能为空")
    @Length(min = 0, max = 128, message = "description长度异常,取值范围0~128")
    private String description;

    @ApiModelProperty(value = "排序（升序）", required = true)
    @NotEmpty(message = "sort不能为空")
    @Length(min = 0, max = 32, message = "sort长度异常,取值范围0~32")
    private String sort;

}
