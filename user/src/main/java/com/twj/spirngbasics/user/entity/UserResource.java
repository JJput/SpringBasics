package com.twj.spirngbasics.user.entity;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.twj.spirngbasics.server.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

import javax.annotation.Generated;
import javax.validation.constraints.NotEmpty;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:37
 * @Version 1.0
 * @描述: 资源
 */
@Data
public class UserResource extends BaseEntity implements Serializable {

    @ApiModelProperty(value = "名称|菜单或按钮", required = true)
    @NotEmpty(message = "name不能为空")
    @Length(min = 0, max = 100, message = "name长度异常,取值范围0~100")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String name;

    @ApiModelProperty(value = "页面|路由")
    @Length(min = 0, max = 50, message = "page长度异常,取值范围0~50")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String page;

    @ApiModelProperty(value = "请求|接口")
    @Length(min = 0, max = 200, message = "request长度异常,取值范围0~200")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String request;

    @ApiModelProperty(value = "父id")
    @Length(min = 0, max = 0, message = "parent长度异常,取值范围0~0")
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String parent;

}