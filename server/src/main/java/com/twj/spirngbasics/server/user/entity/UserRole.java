package com.twj.spirngbasics.server.user.entity;

import com.twj.spirngbasics.server.entity.BaseEntity;
import lombok.Data;

/**
* @作者: Jun
* @创建时间: 2021-01-05 17:27:50
* @Version 1.0
* @描述: 角色
*/
@Data
public class UserRole extends BaseEntity {

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

}
