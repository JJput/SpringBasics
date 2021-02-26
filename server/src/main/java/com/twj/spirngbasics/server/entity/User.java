package com.twj.spirngbasics.server.entity;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.annotation.Generated;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;

/**
 * @作者: Jun
 * @创建时间: 2021-02-24 17:13:25
 * @Version 1.0
 * @描述: Users and global privileges
 */
@Data
public class User extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "名称", required = true)
    @NotEmpty(message = "name不能为空")
    @Length(min = 0, max = 60, message = "name长度异常,取值范围0~60")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @ApiModelProperty(value = "手机号", required = true)
    @NotEmpty(message = "phone不能为空")
    @Length(min = 11, max = 11, message = "请检查手机号")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String phone;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty(message = "pwd不能为空")
    @Length(min = 0, max = 256, message = "pwd长度异常,取值范围0~256")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String pwd;

    private HashSet requests;

    @ApiModelProperty(value = "验证码")
    @NotEmpty(message = "验证码不能为空")
    @Length(min = 6, max = 6, message = "请检查验证码")
    private String code;
}