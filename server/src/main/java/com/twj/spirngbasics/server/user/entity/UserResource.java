package com.twj.spirngbasics.server.user.entity;


import com.twj.spirngbasics.server.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @作者: Jun
 * @创建时间: 2021-01-10 13:53:38
 * @Version 1.0
 * @描述: 资源
 */
@Data
public class UserResource extends BaseEntity {

    @ApiModelProperty("名称|菜单或按钮")
    private String name;

    @ApiModelProperty("页面|路由")
    private String page;

    @ApiModelProperty("请求|接口")
    private String request;

    @ApiModelProperty("父id")
    private String parent;

}