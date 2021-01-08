package com.twj.spirngbasics.server.user.dto;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.twj.spirngbasics.server.entity.BaseEntity;

/**
* @作者: Jun
* @创建时间: 2021-01-05 17:27:51
* @Version 1.0
* @描述: 角色资源关联
*/
@Data
public class UserRoleResourceDto {


    /**
    * id
    */
    private String id;

    /**
    * 角色|id
    */
    private String roleId;

    /**
    * 资源|id
    */
    private String resourceId;

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

}
