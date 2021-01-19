package com.twj.spirngbasics.server.entity;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.twj.spirngbasics.server.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

import javax.annotation.Generated;
import javax.validation.constraints.NotEmpty;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 15:51:40
 * @Version 1.0
 * @描述: 字典表 
 */
@Data
public class SysDict extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "备注")
    @Length(min = 0, max = 512, message = "remake长度异常,取值范围0~512")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String remake;

    @ApiModelProperty(value = "备用1")
    @Length(min = 0, max = 64, message = "spare1长度异常,取值范围0~64")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String spare1;

    @ApiModelProperty(value = "数据值", required = true)
    @NotEmpty(message = "value不能为空")
    @Length(min = 0, max = 128, message = "value长度异常,取值范围0~128")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String value;

    @ApiModelProperty(value = "标签名", required = true)
    @NotEmpty(message = "label不能为空")
    @Length(min = 0, max = 128, message = "label长度异常,取值范围0~128")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String label;

    @ApiModelProperty(value = "类型", required = true)
    @NotEmpty(message = "type不能为空")
    @Length(min = 0, max = 128, message = "type长度异常,取值范围0~128")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String type;

    @ApiModelProperty(value = "描述", required = true)
    @NotEmpty(message = "description不能为空")
    @Length(min = 0, max = 128, message = "description长度异常,取值范围0~128")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String description;

    @ApiModelProperty(value = "排序（升序）", required = true)
    @NotEmpty(message = "sort不能为空")
    @Length(min = 0, max = 32, message = "sort长度异常,取值范围0~32")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String sort;

}