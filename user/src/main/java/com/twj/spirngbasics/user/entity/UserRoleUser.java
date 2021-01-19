package com.twj.spirngbasics.user.entity;


import com.twj.spirngbasics.server.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Generated;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 角色用户关联
 */
@Data
public class UserRoleUser extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "角色|id", required = true)
    @NotEmpty(message = "roleId不能为空")
    @Length(min = 0, max = 32, message = "roleId长度异常,取值范围0~32")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String roleId;

    @ApiModelProperty(value = "用户|id", required = true)
    @NotEmpty(message = "userId不能为空")
    @Length(min = 0, max = 32, message = "userId长度异常,取值范围0~32")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String userId;

}