package com.twj.spirngbasics.user.mapper;

import com.twj.spirngbasics.user.entity.UserRoleResource;
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
public interface UserRoleResourceMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(UserRoleResourceDynamicSqlSupport.id, UserRoleResourceDynamicSqlSupport.roleId, UserRoleResourceDynamicSqlSupport.resourceId, UserRoleResourceDynamicSqlSupport.createdBy, UserRoleResourceDynamicSqlSupport.createdTime, UserRoleResourceDynamicSqlSupport.updateBy, UserRoleResourceDynamicSqlSupport.updateTime, UserRoleResourceDynamicSqlSupport.dele);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<UserRoleResource> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserRoleResource> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserRoleResourceResult")
    Optional<UserRoleResource> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserRoleResourceResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.VARCHAR),
        @Result(column="resource_id", property="resourceId", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="dele", property="dele", jdbcType=JdbcType.VARCHAR)
    })
    List<UserRoleResource> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UserRoleResourceDynamicSqlSupport.userRoleResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UserRoleResourceDynamicSqlSupport.userRoleResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(UserRoleResourceDynamicSqlSupport.id, SqlBuilder.isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(UserRoleResource record) {
        return MyBatis3Utils.insert(this::insert, record, UserRoleResourceDynamicSqlSupport.userRoleResource, c ->
            c.map(UserRoleResourceDynamicSqlSupport.id).toProperty("id")
            .map(UserRoleResourceDynamicSqlSupport.roleId).toProperty("roleId")
            .map(UserRoleResourceDynamicSqlSupport.resourceId).toProperty("resourceId")
            .map(UserRoleResourceDynamicSqlSupport.createdBy).toProperty("createdBy")
            .map(UserRoleResourceDynamicSqlSupport.createdTime).toProperty("createdTime")
            .map(UserRoleResourceDynamicSqlSupport.updateBy).toProperty("updateBy")
            .map(UserRoleResourceDynamicSqlSupport.updateTime).toProperty("updateTime")
            .map(UserRoleResourceDynamicSqlSupport.dele).toProperty("dele")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<UserRoleResource> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, UserRoleResourceDynamicSqlSupport.userRoleResource, c ->
            c.map(UserRoleResourceDynamicSqlSupport.id).toProperty("id")
            .map(UserRoleResourceDynamicSqlSupport.roleId).toProperty("roleId")
            .map(UserRoleResourceDynamicSqlSupport.resourceId).toProperty("resourceId")
            .map(UserRoleResourceDynamicSqlSupport.createdBy).toProperty("createdBy")
            .map(UserRoleResourceDynamicSqlSupport.createdTime).toProperty("createdTime")
            .map(UserRoleResourceDynamicSqlSupport.updateBy).toProperty("updateBy")
            .map(UserRoleResourceDynamicSqlSupport.updateTime).toProperty("updateTime")
            .map(UserRoleResourceDynamicSqlSupport.dele).toProperty("dele")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(UserRoleResource record) {
        return MyBatis3Utils.insert(this::insert, record, UserRoleResourceDynamicSqlSupport.userRoleResource, c ->
            c.map(UserRoleResourceDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId)
            .map(UserRoleResourceDynamicSqlSupport.roleId).toPropertyWhenPresent("roleId", record::getRoleId)
            .map(UserRoleResourceDynamicSqlSupport.resourceId).toPropertyWhenPresent("resourceId", record::getResourceId)
            .map(UserRoleResourceDynamicSqlSupport.createdBy).toPropertyWhenPresent("createdBy", record::getCreatedBy)
            .map(UserRoleResourceDynamicSqlSupport.createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(UserRoleResourceDynamicSqlSupport.updateBy).toPropertyWhenPresent("updateBy", record::getUpdateBy)
            .map(UserRoleResourceDynamicSqlSupport.updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(UserRoleResourceDynamicSqlSupport.dele).toPropertyWhenPresent("dele", record::getDele)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserRoleResource> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UserRoleResourceDynamicSqlSupport.userRoleResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserRoleResource> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UserRoleResourceDynamicSqlSupport.userRoleResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserRoleResource> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UserRoleResourceDynamicSqlSupport.userRoleResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserRoleResource> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(UserRoleResourceDynamicSqlSupport.id, SqlBuilder.isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UserRoleResourceDynamicSqlSupport.userRoleResource, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(UserRoleResource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UserRoleResourceDynamicSqlSupport.id).equalTo(record::getId)
                .set(UserRoleResourceDynamicSqlSupport.roleId).equalTo(record::getRoleId)
                .set(UserRoleResourceDynamicSqlSupport.resourceId).equalTo(record::getResourceId)
                .set(UserRoleResourceDynamicSqlSupport.createdBy).equalTo(record::getCreatedBy)
                .set(UserRoleResourceDynamicSqlSupport.createdTime).equalTo(record::getCreatedTime)
                .set(UserRoleResourceDynamicSqlSupport.updateBy).equalTo(record::getUpdateBy)
                .set(UserRoleResourceDynamicSqlSupport.updateTime).equalTo(record::getUpdateTime)
                .set(UserRoleResourceDynamicSqlSupport.dele).equalTo(record::getDele);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserRoleResource record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UserRoleResourceDynamicSqlSupport.id).equalToWhenPresent(record::getId)
                .set(UserRoleResourceDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
                .set(UserRoleResourceDynamicSqlSupport.resourceId).equalToWhenPresent(record::getResourceId)
                .set(UserRoleResourceDynamicSqlSupport.createdBy).equalToWhenPresent(record::getCreatedBy)
                .set(UserRoleResourceDynamicSqlSupport.createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(UserRoleResourceDynamicSqlSupport.updateBy).equalToWhenPresent(record::getUpdateBy)
                .set(UserRoleResourceDynamicSqlSupport.updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(UserRoleResourceDynamicSqlSupport.dele).equalToWhenPresent(record::getDele);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(UserRoleResource record) {
        return update(c ->
            c.set(UserRoleResourceDynamicSqlSupport.roleId).equalTo(record::getRoleId)
            .set(UserRoleResourceDynamicSqlSupport.resourceId).equalTo(record::getResourceId)
            .set(UserRoleResourceDynamicSqlSupport.createdBy).equalTo(record::getCreatedBy)
            .set(UserRoleResourceDynamicSqlSupport.createdTime).equalTo(record::getCreatedTime)
            .set(UserRoleResourceDynamicSqlSupport.updateBy).equalTo(record::getUpdateBy)
            .set(UserRoleResourceDynamicSqlSupport.updateTime).equalTo(record::getUpdateTime)
            .set(UserRoleResourceDynamicSqlSupport.dele).equalTo(record::getDele)
            .where(UserRoleResourceDynamicSqlSupport.id, SqlBuilder.isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(UserRoleResource record) {
        return update(c ->
            c.set(UserRoleResourceDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
            .set(UserRoleResourceDynamicSqlSupport.resourceId).equalToWhenPresent(record::getResourceId)
            .set(UserRoleResourceDynamicSqlSupport.createdBy).equalToWhenPresent(record::getCreatedBy)
            .set(UserRoleResourceDynamicSqlSupport.createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(UserRoleResourceDynamicSqlSupport.updateBy).equalToWhenPresent(record::getUpdateBy)
            .set(UserRoleResourceDynamicSqlSupport.updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(UserRoleResourceDynamicSqlSupport.dele).equalToWhenPresent(record::getDele)
            .where(UserRoleResourceDynamicSqlSupport.id, SqlBuilder.isEqualTo(record::getId))
        );
    }
}