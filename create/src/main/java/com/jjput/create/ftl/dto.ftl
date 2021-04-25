package ${PACKAGE_DTO};


<#list typeSet as type>
<#if type=='Date'>
import java.util.Date;
</#if>
<#if type=='BigDecimal'>
import java.math.BigDecimal;
</#if>
</#list>
import lombok.Data;
import ${PACKAGE_SERVER}.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;


/**
 * @作者: ${AUTHOR}
 * @创建时间: ${TIME}
 * @Version 1.0
 * @描述: ${tableNameCn}
 */
@Data
public class ${Domain}Dto {

    <#list fieldList as field>
    @ApiModelProperty(value = "${field.comment}"<#if field.nullAble == false>, required = true</#if>)
    <#if field.nullAble == false>
    @NotEmpty(message = "${field.nameHump}不能为空")
    </#if>
    <#if field.javaType=='String'>
    @Length(min = 0, max = ${field.length?c}, message = "${field.nameHump}长度异常,取值范围0~${field.length?c}")
    </#if>
    private <#if field.type == 'bit(1)' ||
    field.type == 'bit' ||
    field.type == 'tinyint(1)' ||
    field.type == 'tinyint'>Boolean<#else>${field.javaType}</#if> ${field.nameHump};

    </#list>
<#--    <#list fieldList as field>-->
<#--    public ${field.javaType} get${field.nameBigHump}() {-->
<#--        return ${field.nameHump};-->
<#--    }-->
<#--    public void set${field.nameBigHump}(${field.javaType} ${field.nameHump}) {-->
<#--        this.${field.nameHump} = ${field.nameHump};-->
<#--    }-->
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
