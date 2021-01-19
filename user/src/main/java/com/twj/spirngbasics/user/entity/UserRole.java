package com.twj.spirngbasics.user.entity;


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
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 角色
 */
@Data
public class UserRole extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "角色", required = true)
    @NotEmpty(message = "name不能为空")
    @Length(min = 0, max = 50, message = "name长度异常,取值范围0~50")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @ApiModelProperty(value = "描述", required = true)
    @NotEmpty(message = "desc不能为空")
    @Length(min = 0, max = 100, message = "desc长度异常,取值范围0~100")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String desc;

    @ApiModelProperty(value = "公司自定义角色", required = true)
    @NotEmpty(message = "companyId不能为空")
    @Length(min = 0, max = 32, message = "companyId长度异常,取值范围0~32")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String companyId;

}