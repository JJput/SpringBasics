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
import ${PACKAGE_ENTITY}.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @作者: ${AUTHOR}
 * @创建时间: ${TIME}
 * @Version 1.0
 * @描述: ${tableNameCn}
 */
@Data
public class ${Domain} extends BaseEntity {

    <#list fieldList as field>
    <#if field.nameHump=='id'>
    <#elseif field.nameHump=='revision'>
    <#elseif field.nameHump=='createdBy'>
    <#elseif field.nameHump=='createdTime'>
    <#elseif field.nameHump=='updateBy'>
    <#elseif field.nameHump=='updateTime'>
    <#elseif field.nameHump=='dele'>
    <#else>
    @ApiModelProperty("${field.comment}")
        <#if field.javaType=='Date'>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        </#if>
    private ${field.javaType} ${field.nameHump};

    </#if>
    </#list>
}