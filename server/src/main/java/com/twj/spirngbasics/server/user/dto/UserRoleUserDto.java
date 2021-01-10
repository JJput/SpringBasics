package com.twj.spirngbasics.server.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @作者: Jun
 * @创建时间: 2021-01-10 13:53:39
 * @Version 1.0
 * @描述: 角色用户关联
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleUserDto {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("角色|id")
    private String roleId;

    @ApiModelProperty("用户|id")
    private String userId;

    @ApiModelProperty("创建人")
    private String createdBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    @ApiModelProperty("更新人")
    private String updateBy;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("删除")
    private String dele;

}
