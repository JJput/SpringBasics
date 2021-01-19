package com.twj.spirngbasics.user.mapper;

import com.twj.spirngbasics.user.entity.UserRole;
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
public interface UserRoleMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(UserRoleDynamicSqlSupport.id, UserRoleDynamicSqlSupport.name, UserRoleDynamicSqlSupport.desc, UserRoleDynamicSqlSupport.companyId, UserRoleDynamicSqlSupport.createdBy, UserRoleDynamicSqlSupport.createdTime, UserRoleDynamicSqlSupport.updateBy, UserRoleDynamicSqlSupport.updateTime, UserRoleDynamicSqlSupport.dele);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<UserRole> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserRole> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserRoleResult")
    Optional<UserRole> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserRoleResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="desc", property="desc", jdbcType=JdbcType.VARCHAR),
        @Result(column="company_id", property="companyId", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="dele", property="dele", jdbcType=JdbcType.VARCHAR)
    })
    List<UserRole> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UserRoleDynamicSqlSupport.userRole, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UserRoleDynamicSqlSupport.userRole, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(UserRoleDynamicSqlSupport.id, SqlBuilder.isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(UserRole record) {
        return MyBatis3Utils.insert(this::insert, record, UserRoleDynamicSqlSupport.userRole, c ->
            c.map(UserRoleDynamicSqlSupport.id).toProperty("id")
            .map(UserRoleDynamicSqlSupport.name).toProperty("name")
            .map(UserRoleDynamicSqlSupport.desc).toProperty("desc")
            .map(UserRoleDynamicSqlSupport.companyId).toProperty("companyId")
            .map(UserRoleDynamicSqlSupport.createdBy).toProperty("createdBy")
            .map(UserRoleDynamicSqlSupport.createdTime).toProperty("createdTime")
            .map(UserRoleDynamicSqlSupport.updateBy).toProperty("updateBy")
            .map(UserRoleDynamicSqlSupport.updateTime).toProperty("updateTime")
            .map(UserRoleDynamicSqlSupport.dele).toProperty("dele")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<UserRole> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, UserRoleDynamicSqlSupport.userRole, c ->
            c.map(UserRoleDynamicSqlSupport.id).toProperty("id")
            .map(UserRoleDynamicSqlSupport.name).toProperty("name")
            .map(UserRoleDynamicSqlSupport.desc).toProperty("desc")
            .map(UserRoleDynamicSqlSupport.companyId).toProperty("companyId")
            .map(UserRoleDynamicSqlSupport.createdBy).toProperty("createdBy")
            .map(UserRoleDynamicSqlSupport.createdTime).toProperty("createdTime")
            .map(UserRoleDynamicSqlSupport.updateBy).toProperty("updateBy")
            .map(UserRoleDynamicSqlSupport.updateTime).toProperty("updateTime")
            .map(UserRoleDynamicSqlSupport.dele).toProperty("dele")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(UserRole record) {
        return MyBatis3Utils.insert(this::insert, record, UserRoleDynamicSqlSupport.userRole, c ->
            c.map(UserRoleDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId)
            .map(UserRoleDynamicSqlSupport.name).toPropertyWhenPresent("name", record::getName)
            .map(UserRoleDynamicSqlSupport.desc).toPropertyWhenPresent("desc", record::getDesc)
            .map(UserRoleDynamicSqlSupport.companyId).toPropertyWhenPresent("companyId", record::getCompanyId)
            .map(UserRoleDynamicSqlSupport.createdBy).toPropertyWhenPresent("createdBy", record::getCreatedBy)
            .map(UserRoleDynamicSqlSupport.createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(UserRoleDynamicSqlSupport.updateBy).toPropertyWhenPresent("updateBy", record::getUpdateBy)
            .map(UserRoleDynamicSqlSupport.updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(UserRoleDynamicSqlSupport.dele).toPropertyWhenPresent("dele", record::getDele)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserRole> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UserRoleDynamicSqlSupport.userRole, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserRole> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UserRoleDynamicSqlSupport.userRole, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserRole> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UserRoleDynamicSqlSupport.userRole, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserRole> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(UserRoleDynamicSqlSupport.id, SqlBuilder.isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UserRoleDynamicSqlSupport.userRole, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(UserRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UserRoleDynamicSqlSupport.id).equalTo(record::getId)
                .set(UserRoleDynamicSqlSupport.name).equalTo(record::getName)
                .set(UserRoleDynamicSqlSupport.desc).equalTo(record::getDesc)
                .set(UserRoleDynamicSqlSupport.companyId).equalTo(record::getCompanyId)
                .set(UserRoleDynamicSqlSupport.createdBy).equalTo(record::getCreatedBy)
                .set(UserRoleDynamicSqlSupport.createdTime).equalTo(record::getCreatedTime)
                .set(UserRoleDynamicSqlSupport.updateBy).equalTo(record::getUpdateBy)
                .set(UserRoleDynamicSqlSupport.updateTime).equalTo(record::getUpdateTime)
                .set(UserRoleDynamicSqlSupport.dele).equalTo(record::getDele);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserRole record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UserRoleDynamicSqlSupport.id).equalToWhenPresent(record::getId)
                .set(UserRoleDynamicSqlSupport.name).equalToWhenPresent(record::getName)
                .set(UserRoleDynamicSqlSupport.desc).equalToWhenPresent(record::getDesc)
                .set(UserRoleDynamicSqlSupport.companyId).equalToWhenPresent(record::getCompanyId)
                .set(UserRoleDynamicSqlSupport.createdBy).equalToWhenPresent(record::getCreatedBy)
                .set(UserRoleDynamicSqlSupport.createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(UserRoleDynamicSqlSupport.updateBy).equalToWhenPresent(record::getUpdateBy)
                .set(UserRoleDynamicSqlSupport.updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(UserRoleDynamicSqlSupport.dele).equalToWhenPresent(record::getDele);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(UserRole record) {
        return update(c ->
            c.set(UserRoleDynamicSqlSupport.name).equalTo(record::getName)
            .set(UserRoleDynamicSqlSupport.desc).equalTo(record::getDesc)
            .set(UserRoleDynamicSqlSupport.companyId).equalTo(record::getCompanyId)
            .set(UserRoleDynamicSqlSupport.createdBy).equalTo(record::getCreatedBy)
            .set(UserRoleDynamicSqlSupport.createdTime).equalTo(record::getCreatedTime)
            .set(UserRoleDynamicSqlSupport.updateBy).equalTo(record::getUpdateBy)
            .set(UserRoleDynamicSqlSupport.updateTime).equalTo(record::getUpdateTime)
            .set(UserRoleDynamicSqlSupport.dele).equalTo(record::getDele)
            .where(UserRoleDynamicSqlSupport.id, SqlBuilder.isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(UserRole record) {
        return update(c ->
            c.set(UserRoleDynamicSqlSupport.name).equalToWhenPresent(record::getName)
            .set(UserRoleDynamicSqlSupport.desc).equalToWhenPresent(record::getDesc)
            .set(UserRoleDynamicSqlSupport.companyId).equalToWhenPresent(record::getCompanyId)
            .set(UserRoleDynamicSqlSupport.createdBy).equalToWhenPresent(record::getCreatedBy)
            .set(UserRoleDynamicSqlSupport.createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(UserRoleDynamicSqlSupport.updateBy).equalToWhenPresent(record::getUpdateBy)
            .set(UserRoleDynamicSqlSupport.updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(UserRoleDynamicSqlSupport.dele).equalToWhenPresent(record::getDele)
            .where(UserRoleDynamicSqlSupport.id, SqlBuilder.isEqualTo(record::getId))
        );
    }
}