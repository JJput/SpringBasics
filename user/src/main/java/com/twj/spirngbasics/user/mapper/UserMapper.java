package com.twj.spirngbasics.user.mapper;

import com.twj.spirngbasics.server.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
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

import static com.twj.spirngbasics.user.mapper.UserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Mapper
public interface UserMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, createdBy, createdTime, updateBy, updateTime, dele, name, phone, pwd);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<User> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<User> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("UserResult")
    Optional<User> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="UserResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="dele", property="dele", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="pwd", property="pwd", jdbcType=JdbcType.VARCHAR)
    })
    List<User> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
            c.map(id).toProperty("id")
            .map(createdBy).toProperty("createdBy")
            .map(createdTime).toProperty("createdTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(dele).toProperty("dele")
            .map(name).toProperty("name")
            .map(phone).toProperty("phone")
            .map(pwd).toProperty("pwd")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<User> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, user, c ->
            c.map(id).toProperty("id")
            .map(createdBy).toProperty("createdBy")
            .map(createdTime).toProperty("createdTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(dele).toProperty("dele")
            .map(name).toProperty("name")
            .map(phone).toProperty("phone")
            .map(pwd).toProperty("pwd")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(User record) {
        return MyBatis3Utils.insert(this::insert, record, user, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(createdBy).toPropertyWhenPresent("createdBy", record::getCreatedBy)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", record::getUpdateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(dele).toPropertyWhenPresent("dele", record::getDele)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(phone).toPropertyWhenPresent("phone", record::getPhone)
            .map(pwd).toPropertyWhenPresent("pwd", record::getPwd)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<User> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<User> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<User> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<User> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, user, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(createdBy).equalTo(record::getCreatedBy)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updateBy).equalTo(record::getUpdateBy)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(dele).equalTo(record::getDele)
                .set(name).equalTo(record::getName)
                .set(phone).equalTo(record::getPhone)
                .set(pwd).equalTo(record::getPwd);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(User record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(createdBy).equalToWhenPresent(record::getCreatedBy)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updateBy).equalToWhenPresent(record::getUpdateBy)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(dele).equalToWhenPresent(record::getDele)
                .set(name).equalToWhenPresent(record::getName)
                .set(phone).equalToWhenPresent(record::getPhone)
                .set(pwd).equalToWhenPresent(record::getPwd);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(User record) {
        return update(c ->
            c.set(createdBy).equalTo(record::getCreatedBy)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updateBy).equalTo(record::getUpdateBy)
            .set(updateTime).equalTo(record::getUpdateTime)
            .set(dele).equalTo(record::getDele)
            .set(name).equalTo(record::getName)
            .set(phone).equalTo(record::getPhone)
            .set(pwd).equalTo(record::getPwd)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(User record) {
        return update(c ->
            c.set(createdBy).equalToWhenPresent(record::getCreatedBy)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updateBy).equalToWhenPresent(record::getUpdateBy)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(dele).equalToWhenPresent(record::getDele)
            .set(name).equalToWhenPresent(record::getName)
            .set(phone).equalToWhenPresent(record::getPhone)
            .set(pwd).equalToWhenPresent(record::getPwd)
            .where(id, isEqualTo(record::getId))
        );
    }
}