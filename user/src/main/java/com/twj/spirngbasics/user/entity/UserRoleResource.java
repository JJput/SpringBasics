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
 * @描述: 角色资源关联
 */
@Data
public class UserRoleResource extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "角色|id", required = true)
    @NotEmpty(message = "roleId不能为空")
    @Length(min = 0, max = 32, message = "roleId长度异常,取值范围0~32")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String roleId;

    @ApiModelProperty(value = "资源|id", required = true)
    @NotEmpty(message = "resourceId不能为空")
    @Length(min = 0, max = 32, message = "resourceId长度异常,取值范围0~32")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String resourceId;

}