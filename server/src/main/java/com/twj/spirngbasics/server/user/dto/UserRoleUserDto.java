package com.twj.spirngbasics.server.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
* @作者: Jun
* @创建时间: 2021-01-05 17:27:51
* @Version 1.0
* @描述: 角色用户关联
*/
@Data
public class UserRoleUserDto {


    /**
    * id
    */
    private String id;

    /**
    * 角色|id
    */
    private String roleId;

    /**
    * 用户|id
    */
    private String userId;

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
