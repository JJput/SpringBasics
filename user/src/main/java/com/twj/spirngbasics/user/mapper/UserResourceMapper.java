package com.twj.spirngbasics.user.mapper;

import com.twj.spirngbasics.user.entity.UserResource;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import javax.annotation.Generated;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Mapper
public interface UserResourceMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(UserResourceDynamicSqlSupport.id, UserResourceDynamicSqlSupport.name, UserResourceDynamicSqlSupport.page, UserResourceDynamicSqlSupport.request, UserResourceDynamicSqlSupport.parent, UserResourceDynamicSqlSupport.createdBy, UserResourceDynamicSqlSupport.createdTime, UserResourceDynamicSqlSupport.updateBy, UserResourceDynamicSqlSupport.updateTime, UserResourceDynamicSqlSupport.dele);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<UserResource> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserResource> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResourceResult")
    Optional<UserResource> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResourceResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="page", property="page", jdbcType=JdbcType.VARCHAR),
        @Result(column="request", property="request", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent", property="parent", jdbcType=JdbcType.CHAR),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="dele", property="dele", jdbcType=JdbcType.VARCHAR)
    })
    List<UserResource> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UserResourceDynamicSqlSupport.userResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UserResourceDynamicSqlSupport.userResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(UserResourceDynamicSqlSupport.id, SqlBuilder.isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(UserResource record) {
        return MyBatis3Utils.insert(this::insert, record, UserResourceDynamicSqlSupport.userResource, c ->
            c.map(UserResourceDynamicSqlSupport.id).toProperty("id")
            .map(UserResourceDynamicSqlSupport.name).toProperty("name")
            .map(UserResourceDynamicSqlSupport.page).toProperty("page")
            .map(UserResourceDynamicSqlSupport.request).toProperty("request")
            .map(UserResourceDynamicSqlSupport.parent).toProperty("parent")
            .map(UserResourceDynamicSqlSupport.createdBy).toProperty("createdBy")
            .map(UserResourceDynamicSqlSupport.createdTime).toProperty("createdTime")
            .map(UserResourceDynamicSqlSupport.updateBy).toProperty("updateBy")
            .map(UserResourceDynamicSqlSupport.updateTime).toProperty("updateTime")
            .map(UserResourceDynamicSqlSupport.dele).toProperty("dele")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<UserResource> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, UserResourceDynamicSqlSupport.userResource, c ->
            c.map(UserResourceDynamicSqlSupport.id).toProperty("id")
            .map(UserResourceDynamicSqlSupport.name).toProperty("name")
            .map(UserResourceDynamicSqlSupport.page).toProperty("page")
            .map(UserResourceDynamicSqlSupport.request).toProperty("request")
            .map(UserResourceDynamicSqlSupport.parent).toProperty("parent")
            .map(UserResourceDynamicSqlSupport.createdBy).toProperty("createdBy")
            .map(UserResourceDynamicSqlSupport.createdTime).toProperty("createdTime")
            .map(UserResourceDynamicSqlSupport.updateBy).toProperty("updateBy")
            .map(UserResourceDynamicSqlSupport.updateTime).toProperty("updateTime")
            .map(UserResourceDynamicSqlSupport.dele).toProperty("dele")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(UserResource record) {
        return MyBatis3Utils.insert(this::insert, record, UserResourceDynamicSqlSupport.userResource, c ->
            c.map(UserResourceDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId)
            .map(UserResourceDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(UserResourceDynamicSqlSupport.page).toPropertyWhenPresent("page", record::getPage)
            .map(UserResourceDynamicSqlSupport.request).toPropertyWhenPresent("request", record::getRequest)
            .map(UserResourceDynamicSqlSupport.parent).toPropertyWhenPresent("parent", record::getParent)
            .map(UserResourceDynamicSqlSupport.createdBy).toPropertyWhenPresent("createdBy", record::getCreatedBy)
            .map(UserResourceDynamicSqlSupport.createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(UserResourceDynamicSqlSupport.updateBy).toPropertyWhenPresent("updateBy", record::getUpdateBy)
            .map(UserResourceDynamicSqlSupport.updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(UserResourceDynamicSqlSupport.dele).toPropertyWhenPresent("dele", record::getDele)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserResource> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UserResourceDynamicSqlSupport.userResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserResource> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UserResourceDynamicSqlSupport.userResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserResource> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UserResourceDynamicSqlSupport.userResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserResource> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(UserResourceDynamicSqlSupport.id, SqlBuilder.isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UserResourceDynamicSqlSupport.userResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(UserResource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UserResourceDynamicSqlSupport.id).equalTo(record::getId)
                .set(UserResourceDynamicSqlSupport.name).equalTo(record::getName)
                .set(UserResourceDynamicSqlSupport.page).equalTo(record::getPage)
                .set(UserResourceDynamicSqlSupport.request).equalTo(record::getRequest)
                .set(UserResourceDynamicSqlSupport.parent).equalTo(record::getParent)
                .set(UserResourceDynamicSqlSupport.createdBy).equalTo(record::getCreatedBy)
                .set(UserResourceDynamicSqlSupport.createdTime).equalTo(record::getCreatedTime)
                .set(UserResourceDynamicSqlSupport.updateBy).equalTo(record::getUpdateBy)
                .set(UserResourceDynamicSqlSupport.updateTime).equalTo(record::getUpdateTime)
                .set(UserResourceDynamicSqlSupport.dele).equalTo(record::getDele);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserResource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UserResourceDynamicSqlSupport.id).equalToWhenPresent(record::getId)
                .set(UserResourceDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UserResourceDynamicSqlSupport.page).equalToWhenPresent(record::getPage)
                .set(UserResourceDynamicSqlSupport.request).equalToWhenPresent(record::getRequest)
                .set(UserResourceDynamicSqlSupport.parent).equalToWhenPresent(record::getParent)
                .set(UserResourceDynamicSqlSupport.createdBy).equalToWhenPresent(record::getCreatedBy)
                .set(UserResourceDynamicSqlSupport.createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(UserResourceDynamicSqlSupport.updateBy).equalToWhenPresent(record::getUpdateBy)
                .set(UserResourceDynamicSqlSupport.updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(UserResourceDynamicSqlSupport.dele).equalToWhenPresent(record::getDele);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(UserResource record) {
        return update(c ->
            c.set(UserResourceDynamicSqlSupport.name).equalTo(record::getName)
            .set(UserResourceDynamicSqlSupport.page).equalTo(record::getPage)
            .set(UserResourceDynamicSqlSupport.request).equalTo(record::getRequest)
            .set(UserResourceDynamicSqlSupport.parent).equalTo(record::getParent)
            .set(UserResourceDynamicSqlSupport.createdBy).equalTo(record::getCreatedBy)
            .set(UserResourceDynamicSqlSupport.createdTime).equalTo(record::getCreatedTime)
            .set(UserResourceDynamicSqlSupport.updateBy).equalTo(record::getUpdateBy)
            .set(UserResourceDynamicSqlSupport.updateTime).equalTo(record::getUpdateTime)
            .set(UserResourceDynamicSqlSupport.dele).equalTo(record::getDele)
            .where(UserResourceDynamicSqlSupport.id, SqlBuilder.isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(UserResource record) {
        return update(c ->
            c.set(UserResourceDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(UserResourceDynamicSqlSupport.page).equalToWhenPresent(record::getPage)
            .set(UserResourceDynamicSqlSupport.request).equalToWhenPresent(record::getRequest)
            .set(UserResourceDynamicSqlSupport.parent).equalToWhenPresent(record::getParent)
            .set(UserResourceDynamicSqlSupport.createdBy).equalToWhenPresent(record::getCreatedBy)
            .set(UserResourceDynamicSqlSupport.createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(UserResourceDynamicSqlSupport.updateBy).equalToWhenPresent(record::getUpdateBy)
            .set(UserResourceDynamicSqlSupport.updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(UserResourceDynamicSqlSupport.dele).equalToWhenPresent(record::getDele)
            .where(UserResourceDynamicSqlSupport.id, SqlBuilder.isEqualTo(record::getId))
        );
    }
}