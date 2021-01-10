package com.twj.spirngbasics.server.user.entity;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.twj.spirngbasics.server.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @作者: Jun
 * @创建时间: 2021-01-10 13:53:39
 * @Version 1.0
 * @描述: 角色资源关联
 */
@Data
public class UserRoleResource extends BaseEntity {

    @ApiModelProperty("角色|id")
    private String roleId;

    @ApiModelProperty("资源|id")
    private String resourceId;

}