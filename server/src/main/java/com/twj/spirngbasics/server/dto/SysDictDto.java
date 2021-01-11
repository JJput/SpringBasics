package com.twj.spirngbasics.server.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @作者: Jun
 * @创建时间: 2021-01-06 15:35:31
 * @Version 1.0
 * @描述: 字典表 
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDictDto {

    @ApiModelProperty("备注")
    private String remake;

    @ApiModelProperty("备用1")
    private String spare1;

    private String id;

    @ApiModelProperty("数据值")
    private String value;

    @ApiModelProperty("标签名")
    private String label;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("排序（升序）")
    private String sort;

}
