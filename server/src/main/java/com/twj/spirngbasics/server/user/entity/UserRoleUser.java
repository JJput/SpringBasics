package com.twj.spirngbasics.server.user.entity;

import lombok.Data;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.twj.spirngbasics.server.entity.BaseEntity;

/**
* @作者: Jun
* @创建时间: 2021-01-05 17:27:51
* @Version 1.0
* @描述: 角色用户关联
*/
@Data
public class UserRoleUser extends BaseEntity {

    /**
    * 角色|id
    */
    private String roleId;
    /**
    * 用户|id
    */
    private String userId;

}
