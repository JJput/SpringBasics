package com.twj.spirngbasics.server.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
* @作者: Jun
* @创建时间: 2021-01-05 17:27:50
* @Version 1.0
* @描述: 角色
*/
@Data
public class UserRoleDto {


    /**
    * id
    */
    private String id;

    /**
    * 角色
    */
    private String name;

    /**
    * 描述
    */
    private String desc;

    /**
    * 公司自定义角色
    */
    private String companyId;

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

    private List<String> resourceIds;

    private List<String> userIds;
}
