package com.twj.spirngbasics.server.user.entity;


import com.twj.spirngbasics.server.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @作者: Jun
 * @创建时间: 2021-01-10 13:53:39
 * @Version 1.0
 * @描述: 角色用户关联
 */
@Data
public class UserRoleUser extends BaseEntity {

    @ApiModelProperty("角色|id")
    private String roleId;

    @ApiModelProperty("用户|id")
    private String userId;

}