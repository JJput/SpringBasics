package com.twj.spirngbasics.user.mapper;

import com.twj.spirngbasics.user.entity.UserRoleUser;
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
public interface UserRoleUserMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(UserRoleUserDynamicSqlSupport.id, UserRoleUserDynamicSqlSupport.roleId, UserRoleUserDynamicSqlSupport.userId, UserRoleUserDynamicSqlSupport.createdBy, UserRoleUserDynamicSqlSupport.createdTime, UserRoleUserDynamicSqlSupport.updateBy, UserRoleUserDynamicSqlSupport.updateTime, UserRoleUserDynamicSqlSupport.dele);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<UserRoleUser> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<UserRoleUser> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserRoleUserResult")
    Optional<UserRoleUser> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserRoleUserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="dele", property="dele", jdbcType=JdbcType.VARCHAR)
    })
    List<UserRoleUser> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, UserRoleUserDynamicSqlSupport.userRoleUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, UserRoleUserDynamicSqlSupport.userRoleUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(UserRoleUserDynamicSqlSupport.id, SqlBuilder.isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(UserRoleUser record) {
        return MyBatis3Utils.insert(this::insert, record, UserRoleUserDynamicSqlSupport.userRoleUser, c ->
            c.map(UserRoleUserDynamicSqlSupport.id).toProperty("id")
            .map(UserRoleUserDynamicSqlSupport.roleId).toProperty("roleId")
            .map(UserRoleUserDynamicSqlSupport.userId).toProperty("userId")
            .map(UserRoleUserDynamicSqlSupport.createdBy).toProperty("createdBy")
            .map(UserRoleUserDynamicSqlSupport.createdTime).toProperty("createdTime")
            .map(UserRoleUserDynamicSqlSupport.updateBy).toProperty("updateBy")
            .map(UserRoleUserDynamicSqlSupport.updateTime).toProperty("updateTime")
            .map(UserRoleUserDynamicSqlSupport.dele).toProperty("dele")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<UserRoleUser> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, UserRoleUserDynamicSqlSupport.userRoleUser, c ->
            c.map(UserRoleUserDynamicSqlSupport.id).toProperty("id")
            .map(UserRoleUserDynamicSqlSupport.roleId).toProperty("roleId")
            .map(UserRoleUserDynamicSqlSupport.userId).toProperty("userId")
            .map(UserRoleUserDynamicSqlSupport.createdBy).toProperty("createdBy")
            .map(UserRoleUserDynamicSqlSupport.createdTime).toProperty("createdTime")
            .map(UserRoleUserDynamicSqlSupport.updateBy).toProperty("updateBy")
            .map(UserRoleUserDynamicSqlSupport.updateTime).toProperty("updateTime")
            .map(UserRoleUserDynamicSqlSupport.dele).toProperty("dele")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(UserRoleUser record) {
        return MyBatis3Utils.insert(this::insert, record, UserRoleUserDynamicSqlSupport.userRoleUser, c ->
            c.map(UserRoleUserDynamicSqlSupport.id).toPropertyWhenPresent("id", record::getId)
            .map(UserRoleUserDynamicSqlSupport.roleId).toPropertyWhenPresent("roleId", record::getRoleId)
            .map(UserRoleUserDynamicSqlSupport.userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(UserRoleUserDynamicSqlSupport.createdBy).toPropertyWhenPresent("createdBy", record::getCreatedBy)
            .map(UserRoleUserDynamicSqlSupport.createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(UserRoleUserDynamicSqlSupport.updateBy).toPropertyWhenPresent("updateBy", record::getUpdateBy)
            .map(UserRoleUserDynamicSqlSupport.updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(UserRoleUserDynamicSqlSupport.dele).toPropertyWhenPresent("dele", record::getDele)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserRoleUser> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, UserRoleUserDynamicSqlSupport.userRoleUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserRoleUser> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, UserRoleUserDynamicSqlSupport.userRoleUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<UserRoleUser> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, UserRoleUserDynamicSqlSupport.userRoleUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<UserRoleUser> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(UserRoleUserDynamicSqlSupport.id, SqlBuilder.isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, UserRoleUserDynamicSqlSupport.userRoleUser, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(UserRoleUser record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UserRoleUserDynamicSqlSupport.id).equalTo(record::getId)
                .set(UserRoleUserDynamicSqlSupport.roleId).equalTo(record::getRoleId)
                .set(UserRoleUserDynamicSqlSupport.userId).equalTo(record::getUserId)
                .set(UserRoleUserDynamicSqlSupport.createdBy).equalTo(record::getCreatedBy)
                .set(UserRoleUserDynamicSqlSupport.createdTime).equalTo(record::getCreatedTime)
                .set(UserRoleUserDynamicSqlSupport.updateBy).equalTo(record::getUpdateBy)
                .set(UserRoleUserDynamicSqlSupport.updateTime).equalTo(record::getUpdateTime)
                .set(UserRoleUserDynamicSqlSupport.dele).equalTo(record::getDele);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(UserRoleUser record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(UserRoleUserDynamicSqlSupport.id).equalToWhenPresent(record::getId)
                .set(UserRoleUserDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
                .set(UserRoleUserDynamicSqlSupport.userId).equalToWhenPresent(record::getUserId)
                .set(UserRoleUserDynamicSqlSupport.createdBy).equalToWhenPresent(record::getCreatedBy)
                .set(UserRoleUserDynamicSqlSupport.createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(UserRoleUserDynamicSqlSupport.updateBy).equalToWhenPresent(record::getUpdateBy)
                .set(UserRoleUserDynamicSqlSupport.updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(UserRoleUserDynamicSqlSupport.dele).equalToWhenPresent(record::getDele);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(UserRoleUser record) {
        return update(c ->
            c.set(UserRoleUserDynamicSqlSupport.roleId).equalTo(record::getRoleId)
            .set(UserRoleUserDynamicSqlSupport.userId).equalTo(record::getUserId)
            .set(UserRoleUserDynamicSqlSupport.createdBy).equalTo(record::getCreatedBy)
            .set(UserRoleUserDynamicSqlSupport.createdTime).equalTo(record::getCreatedTime)
            .set(UserRoleUserDynamicSqlSupport.updateBy).equalTo(record::getUpdateBy)
            .set(UserRoleUserDynamicSqlSupport.updateTime).equalTo(record::getUpdateTime)
            .set(UserRoleUserDynamicSqlSupport.dele).equalTo(record::getDele)
            .where(UserRoleUserDynamicSqlSupport.id, SqlBuilder.isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(UserRoleUser record) {
        return update(c ->
            c.set(UserRoleUserDynamicSqlSupport.roleId).equalToWhenPresent(record::getRoleId)
            .set(UserRoleUserDynamicSqlSupport.userId).equalToWhenPresent(record::getUserId)
            .set(UserRoleUserDynamicSqlSupport.createdBy).equalToWhenPresent(record::getCreatedBy)
            .set(UserRoleUserDynamicSqlSupport.createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(UserRoleUserDynamicSqlSupport.updateBy).equalToWhenPresent(record::getUpdateBy)
            .set(UserRoleUserDynamicSqlSupport.updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(UserRoleUserDynamicSqlSupport.dele).equalToWhenPresent(record::getDele)
            .where(UserRoleUserDynamicSqlSupport.id, SqlBuilder.isEqualTo(record::getId))
        );
    }
}