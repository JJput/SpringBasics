package com.twj.spirngbasics.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;


/**
 * @作者: Jun
 * @创建时间: 2021-02-24 17:13:25
 * @Version 1.0
 * @描述: Users and global privileges
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @ApiModelProperty(value = "id", required = true)
    @NotEmpty(message = "id不能为空")
    @Length(min = 0, max = 32, message = "id长度异常,取值范围0~32")
    private String id;


    @ApiModelProperty(value = "名称", required = true)
    @NotEmpty(message = "name不能为空")
    @Length(min = 0, max = 60, message = "name长度异常,取值范围0~60")
    private String name;

    @ApiModelProperty(value = "手机号", required = true)
    @NotEmpty(message = "phone不能为空")
    @Length(min = 0, max = 11, message = "phone长度异常,取值范围0~11")
    private String phone;

    @ApiModelProperty(value = "验证码")
    private String code;

}
