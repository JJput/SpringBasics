package com.twj.spirngbasics.server.user.entity;

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
public class UserRoleResource extends BaseEntity {

    /**
    * 角色|id
    */
    private String roleId;
    /**
    * 资源|id
    */
    private String resourceId;

}
