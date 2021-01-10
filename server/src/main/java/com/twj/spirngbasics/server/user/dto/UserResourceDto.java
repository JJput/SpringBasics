package com.twj.spirngbasics.server.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * @作者: Jun
 * @创建时间: 2021-01-10 13:53:38
 * @Version 1.0
 * @描述: 资源
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResourceDto {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("名称|菜单或按钮")
    private String name;

    @ApiModelProperty("页面|路由")
    private String page;

    @ApiModelProperty("请求|接口")
    private String request;

    @ApiModelProperty("父id")
    private String parent;

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

    private List<UserResourceDto> children;
}
