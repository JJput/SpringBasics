package com.twj.spirngbasics.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;


/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 角色用户关联
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleUserDto {

    @ApiModelProperty(value = "id", required = true)
    @NotEmpty(message = "id不能为空")
    @Length(min = 0, max = 32, message = "id长度异常,取值范围0~32")
    private String id;

    @ApiModelProperty(value = "角色|id", required = true)
    @NotEmpty(message = "roleId不能为空")
    @Length(min = 0, max = 32, message = "roleId长度异常,取值范围0~32")
    private String roleId;

    @ApiModelProperty(value = "用户|id", required = true)
    @NotEmpty(message = "userId不能为空")
    @Length(min = 0, max = 32, message = "userId长度异常,取值范围0~32")
    private String userId;

    @ApiModelProperty(value = "创建人", required = true)
    @NotEmpty(message = "createdBy不能为空")
    @Length(min = 0, max = 32, message = "createdBy长度异常,取值范围0~32")
    private String createdBy;

    @ApiModelProperty(value = "创建时间", required = true)
    @NotEmpty(message = "createdTime不能为空")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty(value = "更新人")
    @Length(min = 0, max = 32, message = "updateBy长度异常,取值范围0~32")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "删除")
    @Length(min = 0, max = 1, message = "dele长度异常,取值范围0~1")
    private String dele;

}
