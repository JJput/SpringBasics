package com.twj.spirngbasics.server.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @作者: Jun
 * @创建时间: 2021-01-10 13:53:39
 * @Version 1.0
 * @描述: 角色
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleDto {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("角色")
    private String name;

    @ApiModelProperty("描述")
    private String desc;

    @ApiModelProperty("公司自定义角色")
    private String companyId;

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

    private List<String> resourceIds;

    private List<String> userIds;
}
