package ${PACKAGE_ENTITY};

import lombok.Data;
<#list typeSet as type>
<#if type=='Date'>
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import ${PACKAGE_ENTITY}.BaseEntity;
</#if>
<#if type=='BigDecimal'>
import java.math.BigDecimal;
</#if>
</#list>

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
    /**
    * ${field.comment}
    */
        <#if field.javaType=='Date'>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        </#if>
    private ${field.javaType} ${field.nameHump};
    </#if>
    </#list>
<#--    <#list fieldList as field>-->
<#--    <#if field.nameHump=='id'>-->
<#--    <#elseif field.nameHump=='revision'>-->
<#--    <#elseif field.nameHump=='createdBy'>-->
<#--    <#elseif field.nameHump=='createdTime'>-->
<#--    <#elseif field.nameHump=='updateBy'>-->
<#--    <#elseif field.nameHump=='updateTime'>-->
<#--    <#elseif field.nameHump=='dele'>-->
<#--    <#else>-->

<#--    public ${field.javaType} get${field.nameBigHump}() {-->
<#--        return ${field.nameHump};-->
<#--    }-->

<#--    public void set${field.nameBigHump}(${field.javaType} ${field.nameHump}) {-->
<#--        this.${field.nameHump} = ${field.nameHump};-->
<#--    }-->
<#--    </#if>-->
<#--    </#list>-->

<#--    @Override-->
<#--    public String toString() {-->
<#--        StringBuilder sb = new StringBuilder();-->
<#--        sb.append(getClass().getSimpleName());-->
<#--        sb.append(" [");-->
<#--        sb.append("Hash = ").append(hashCode());-->
<#--        <#list fieldList as field>-->
<#--        sb.append(", ${field.nameHump}=").append(${field.nameHump});-->
<#--        </#list>-->
<#--        sb.append("]");-->
<#--        return sb.toString();-->
<#--    }-->

}
