package com.twj.spirngbasics.server.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* @作者: Jun
* @创建时间: 2021-01-05 17:27:49
* @Version 1.0
* @描述: 资源
*/
@Data
public class UserResourceDto {


    /**
    * id
    */
    private String id;

    /**
    * 名称|菜单或按钮
    */
    private String name;

    /**
    * 页面|路由
    */
    private String page;

    /**
    * 请求|接口
    */
    private String request;

    /**
    * 父id
    */
    private String parent;

    /**
    * 创建人
    */
    private String createdBy;

    /**
    * 创建时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    /**
    * 更新人
    */
    private String updateBy;

    /**
    * 更新时间
    */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    /**
    * 删除
    */
    private String dele;

    private List<UserResourceDto> children;
}
