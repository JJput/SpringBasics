package ${PACKAGE_ENTITY};


<#list typeSet as type>
<#if type=='Date'>
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
</#if>
<#if type=='BigDecimal'>
import java.math.BigDecimal;
</#if>
</#list>
import lombok.Data;
import ${PACKAGE_SERVER}.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

import javax.annotation.Generated;
import javax.validation.constraints.NotEmpty;

/**
 * @作者: ${AUTHOR}
 * @创建时间: ${TIME}
 * @Version 1.0
 * @描述: ${tableNameCn}
 */
@Data
public class ${Domain} extends BaseEntity implements Serializable {

    <#list fieldList as field>
    <#if field.nameHump=='id'>
    <#elseif field.nameHump=='revision'>
    <#elseif field.nameHump=='createdBy'>
    <#elseif field.nameHump=='createdTime'>
    <#elseif field.nameHump=='updateBy'>
    <#elseif field.nameHump=='updateTime'>
    <#elseif field.nameHump=='dele'>
    <#else>
    @ApiModelProperty(value = "${field.comment}"<#if field.nullAble == false>, required = true</#if>)
        <#if field.javaType=='Date'>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        </#if>
    <#if field.nullAble == false>
        <#if  field.javaType=='String'>
    @NotEmpty(message = "${field.nameHump}不能为空")
        </#if>
        <#if  field.javaType=='Integer'>
    @Range(min = 0, max = 99, message = "${field.nameHump}超限")
        </#if>
    </#if>
    <#if field.javaType=='String'>
    @Length(min = 0, max = ${field.length?c}, message = "${field.nameHump}长度异常,取值范围0~${field.length?c}")
    </#if>
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private ${field.javaType} ${field.nameHump};

    </#if>
    </#list>
}