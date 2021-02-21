package ${PACKAGE_SERVICE};

import lombok.extern.slf4j.Slf4j;
import ${PACKAGE_ENTITY}.${Domain};
import ${PACKAGE_DTO}.${Domain}Dto;
import ${PACKAGE_SERVER}.dto.PageDto;
import ${PACKAGE_MAPPER}.${Domain}Mapper;
import ${PACKAGE_MAPPER}.${Domain}DynamicSqlSupport;
import ${PACKAGE_SERVER}.util.CopyUtils;
import ${PACKAGE_SERVER}.util.ValidatorUtils;
import ${PACKAGE_SERVER}.entity.BaseEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
<#list typeSet as type>
    <#if type=='Date'>
        import java.util.Date;
    </#if>
</#list>


import javax.annotation.Resource;
import java.util.List;


import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @作者: ${AUTHOR}
 * @创建时间: ${TIME}
 * @Version 1.0
 * @描述: ${tableNameCn}
 */
@Service
@Slf4j
public class ${Domain}Service {

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    public ${Domain}Dto findById(String id) {
        ValidatorUtils.require(id, "参数不能为空");
        return CopyUtils.copy(${domain}Mapper.selectByPrimaryKey(id).get(),${Domain}Dto.class);
    }

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SelectStatementProvider selectStatement = SqlBuilder.select(${domain}Mapper.selectList)
                .from(${Domain}DynamicSqlSupport.${domain})
                .where(${Domain}DynamicSqlSupport.dele, SqlBuilder.isEqualTo(BaseEntity.DELE_USE))
    <#list fieldList as field>
        <#if field.nameHump=='sort'>
                .orderBy(${Domain}DynamicSqlSupport.sort)
        </#if>
    </#list>
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<${Domain}Dto> ${domain}DtoList = CopyUtils.copyList(${domain}Mapper.selectMany(selectStatement), ${Domain}Dto.class);
        pageDto.setTotal(${domain}DtoList.size());
        pageDto.setList(${domain}DtoList);
    }

<#--    /**-->
<#--     * 保存，id有值时更新，无值时新增-->
<#--     */-->
<#--    public void save(${Domain} ${domain}) throws Exception{-->
<#--        // 保存校验-->
<#--        <#list fieldList as field>-->
<#--            <#if field.name!="id" && field.nameHump!="createdBy" && field.nameHump!="updatedBy" && field.nameHump!="createdTime" && field.nameHump!="updateTime" && field.nameHump!="dele">-->
<#--                <#if !field.nullAble>-->
<#--        ValidatorUtils.require(${domain}.get${field.nameBigHump}(), "${field.nameCn}");-->
<#--                </#if>-->
<#--                <#if (field.length > 0)>-->
<#--        ValidatorUtils.length(${domain}.get${field.nameBigHump}(), "${field.nameCn}:Length overrun", ${field.length?c});-->
<#--                </#if>-->
<#--            </#if>-->
<#--        </#list>-->
<#--        if (StringUtils.isEmpty(${domain}.getId())) {-->
<#--            this.insert(${domain});-->
<#--        } else {-->
<#--            this.update(${domain});-->
<#--        }-->
<#--    }-->
    /**
     * 新增
     */
    public void insert(${Domain} ${domain}) {
        ${domain}.insert();
        ${domain}Mapper.insert(${domain});
    }

    /**
     * 更新
     */
    public void update(${Domain} ${domain}) {
        ${domain}.update();
        ${domain}Mapper.updateByPrimaryKeySelective(${domain});
    }

    /**
     * 删除
     */
    public void delete(String id) {
        ${Domain} ${domain} = new ${Domain}();
        ${domain}.setId(id);
        ${domain}.update();
        ${domain}.del();
        ${domain}Mapper.updateByPrimaryKeySelective(${domain});
    }
}
