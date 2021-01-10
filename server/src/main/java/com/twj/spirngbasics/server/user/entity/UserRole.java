package com.twj.spirngbasics.server.user.entity;


import com.twj.spirngbasics.server.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @作者: Jun
 * @创建时间: 2021-01-10 13:53:39
 * @Version 1.0
 * @描述: 角色
 */
@Data
public class UserRole extends BaseEntity {

    @ApiModelProperty("角色")
    private String name;

    @ApiModelProperty("描述")
    private String desc;

    @ApiModelProperty("公司自定义角色")
    private String companyId;

}