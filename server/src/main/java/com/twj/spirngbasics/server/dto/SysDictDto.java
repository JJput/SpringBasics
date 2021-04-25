package com.twj.spirngbasics.server.dto;


import java.util.Date;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.twj.spirngbasics.server.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;


/**
 * @作者: Jun
 * @创建时间: 2021-01-21 15:56:06
 * @Version 1.0
 * @描述: 字典表 
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDictDto {

    @ApiModelProperty(value = "创建人", required = true)
    @NotEmpty(message = "createdBy不能为空")
    @Length(min = 0, max = 32, message = "createdBy长度异常,取值范围0~32")
    private String createdBy;

    @ApiModelProperty(value = "创建时间", required = true)
    @NotEmpty(message = "createdTime不能为空")
    private Date createdTime;

    @ApiModelProperty(value = "更新人")
    @Length(min = 0, max = 32, message = "updateBy长度异常,取值范围0~32")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除", required = true)
    @NotEmpty(message = "dele不能为空")
    @Length(min = 0, max = 1, message = "dele长度异常,取值范围0~1")
    private String dele;

    @ApiModelProperty(value = "备注")
    @Length(min = 0, max = 512, message = "remake长度异常,取值范围0~512")
    private String remake;

    @ApiModelProperty(value = "备用1")
    @Length(min = 0, max = 64, message = "spare1长度异常,取值范围0~64")
    private String spare1;

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
    private Integer sort;

}
