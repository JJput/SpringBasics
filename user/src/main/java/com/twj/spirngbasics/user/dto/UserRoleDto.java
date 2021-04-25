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
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 角色
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRoleDto {

    @ApiModelProperty(value = "id", required = true)
    @NotEmpty(message = "id不能为空")
    @Length(min = 0, max = 32, message = "id长度异常,取值范围0~32")
    private String id;

    @ApiModelProperty(value = "角色", required = true)
    @NotEmpty(message = "name不能为空")
    @Length(min = 0, max = 50, message = "name长度异常,取值范围0~50")
    private String name;

    @ApiModelProperty(value = "描述", required = true)
    @NotEmpty(message = "desc不能为空")
    @Length(min = 0, max = 100, message = "desc长度异常,取值范围0~100")
    private String desc;

    @ApiModelProperty(value = "公司自定义角色", required = true)
    @NotEmpty(message = "companyId不能为空")
    @Length(min = 0, max = 32, message = "companyId长度异常,取值范围0~32")
    private String companyId;

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

    @ApiModelProperty(value = "资源列表")
    private List<String> resourceIds;

    @ApiModelProperty(value = "用户id列表")
    private List<String> userIds;
}
