package com.twj.spirngbasics.user.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;


/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:37
 * @Version 1.0
 * @描述: 资源
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResourceDto {

    @ApiModelProperty(value = "id", required = true)
    @NotEmpty(message = "id不能为空")
    @Length(min = 0, max = 32, message = "id长度异常,取值范围0~32")
    private String id;

    @ApiModelProperty(value = "名称|菜单或按钮", required = true)
    @NotEmpty(message = "name不能为空")
    @Length(min = 0, max = 100, message = "name长度异常,取值范围0~100")
    private String name;

    @ApiModelProperty(value = "页面|路由")
    @Length(min = 0, max = 50, message = "page长度异常,取值范围0~50")
    private String page;

    @ApiModelProperty(value = "请求|接口")
    @Length(min = 0, max = 200, message = "request长度异常,取值范围0~200")
    private String request;

    @ApiModelProperty(value = "父id")
    @Length(min = 0, max = 0, message = "parent长度异常,取值范围0~0")
    private String parent;

    @ApiModelProperty(value = "创建人", required = true)
    @NotEmpty(message = "createdBy不能为空")
    @Length(min = 0, max = 32, message = "createdBy长度异常,取值范围0~32")
    private String createdBy;

    @ApiModelProperty(value = "创建时间", required = true)
    @NotEmpty(message = "createdTime不能为空")
    private Date createdTime;

    @ApiModelProperty(value = "更新人")
    @Length(min = 0, max = 32, message = "updateBy长度异常,取值范围0~32")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除")
    @Length(min = 0, max = 1, message = "dele长度异常,取值范围0~1")
    private String dele;

    @ApiModelProperty(value = "子级")
    private List<UserResourceDto> children;
}
