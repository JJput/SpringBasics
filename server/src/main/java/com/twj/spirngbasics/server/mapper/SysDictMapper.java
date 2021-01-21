package com.twj.spirngbasics.server.mapper;

import static com.twj.spirngbasics.server.mapper.SysDictDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.twj.spirngbasics.server.entity.SysDict;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
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

@Mapper
public interface SysDictMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    BasicColumn[] selectList = BasicColumn.columnList(id, createdBy, createdTime, updateBy, updateTime, dele, remake, spare1, value, label, type, description, sort);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<SysDict> insertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<SysDict> multipleInsertStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("SysDictResult")
    Optional<SysDict> selectOne(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="SysDictResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="created_by", property="createdBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="created_time", property="createdTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="dele", property="dele", jdbcType=JdbcType.VARCHAR),
        @Result(column="remake", property="remake", jdbcType=JdbcType.VARCHAR),
        @Result(column="spare1", property="spare1", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="label", property="label", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<SysDict> selectMany(SelectStatementProvider selectStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, sysDict, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, sysDict, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int deleteByPrimaryKey(String id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insert(SysDict record) {
        return MyBatis3Utils.insert(this::insert, record, sysDict, c ->
            c.map(id).toProperty("id")
            .map(createdBy).toProperty("createdBy")
            .map(createdTime).toProperty("createdTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(dele).toProperty("dele")
            .map(remake).toProperty("remake")
            .map(spare1).toProperty("spare1")
            .map(value).toProperty("value")
            .map(label).toProperty("label")
            .map(type).toProperty("type")
            .map(description).toProperty("description")
            .map(sort).toProperty("sort")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertMultiple(Collection<SysDict> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, sysDict, c ->
            c.map(id).toProperty("id")
            .map(createdBy).toProperty("createdBy")
            .map(createdTime).toProperty("createdTime")
            .map(updateBy).toProperty("updateBy")
            .map(updateTime).toProperty("updateTime")
            .map(dele).toProperty("dele")
            .map(remake).toProperty("remake")
            .map(spare1).toProperty("spare1")
            .map(value).toProperty("value")
            .map(label).toProperty("label")
            .map(type).toProperty("type")
            .map(description).toProperty("description")
            .map(sort).toProperty("sort")
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int insertSelective(SysDict record) {
        return MyBatis3Utils.insert(this::insert, record, sysDict, c ->
            c.map(id).toPropertyWhenPresent("id", record::getId)
            .map(createdBy).toPropertyWhenPresent("createdBy", record::getCreatedBy)
            .map(createdTime).toPropertyWhenPresent("createdTime", record::getCreatedTime)
            .map(updateBy).toPropertyWhenPresent("updateBy", record::getUpdateBy)
            .map(updateTime).toPropertyWhenPresent("updateTime", record::getUpdateTime)
            .map(dele).toPropertyWhenPresent("dele", record::getDele)
            .map(remake).toPropertyWhenPresent("remake", record::getRemake)
            .map(spare1).toPropertyWhenPresent("spare1", record::getSpare1)
            .map(value).toPropertyWhenPresent("value", record::getValue)
            .map(label).toPropertyWhenPresent("label", record::getLabel)
            .map(type).toPropertyWhenPresent("type", record::getType)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
            .map(sort).toPropertyWhenPresent("sort", record::getSort)
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SysDict> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, sysDict, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SysDict> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, sysDict, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default List<SysDict> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, sysDict, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default Optional<SysDict> selectByPrimaryKey(String id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, sysDict, completer);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateAllColumns(SysDict record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalTo(record::getId)
                .set(createdBy).equalTo(record::getCreatedBy)
                .set(createdTime).equalTo(record::getCreatedTime)
                .set(updateBy).equalTo(record::getUpdateBy)
                .set(updateTime).equalTo(record::getUpdateTime)
                .set(dele).equalTo(record::getDele)
                .set(remake).equalTo(record::getRemake)
                .set(spare1).equalTo(record::getSpare1)
                .set(value).equalTo(record::getValue)
                .set(label).equalTo(record::getLabel)
                .set(type).equalTo(record::getType)
                .set(description).equalTo(record::getDescription)
                .set(sort).equalTo(record::getSort);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(SysDict record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(id).equalToWhenPresent(record::getId)
                .set(createdBy).equalToWhenPresent(record::getCreatedBy)
                .set(createdTime).equalToWhenPresent(record::getCreatedTime)
                .set(updateBy).equalToWhenPresent(record::getUpdateBy)
                .set(updateTime).equalToWhenPresent(record::getUpdateTime)
                .set(dele).equalToWhenPresent(record::getDele)
                .set(remake).equalToWhenPresent(record::getRemake)
                .set(spare1).equalToWhenPresent(record::getSpare1)
                .set(value).equalToWhenPresent(record::getValue)
                .set(label).equalToWhenPresent(record::getLabel)
                .set(type).equalToWhenPresent(record::getType)
                .set(description).equalToWhenPresent(record::getDescription)
                .set(sort).equalToWhenPresent(record::getSort);
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKey(SysDict record) {
        return update(c ->
            c.set(createdBy).equalTo(record::getCreatedBy)
            .set(createdTime).equalTo(record::getCreatedTime)
            .set(updateBy).equalTo(record::getUpdateBy)
            .set(updateTime).equalTo(record::getUpdateTime)
            .set(dele).equalTo(record::getDele)
            .set(remake).equalTo(record::getRemake)
            .set(spare1).equalTo(record::getSpare1)
            .set(value).equalTo(record::getValue)
            .set(label).equalTo(record::getLabel)
            .set(type).equalTo(record::getType)
            .set(description).equalTo(record::getDescription)
            .set(sort).equalTo(record::getSort)
            .where(id, isEqualTo(record::getId))
        );
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    default int updateByPrimaryKeySelective(SysDict record) {
        return update(c ->
            c.set(createdBy).equalToWhenPresent(record::getCreatedBy)
            .set(createdTime).equalToWhenPresent(record::getCreatedTime)
            .set(updateBy).equalToWhenPresent(record::getUpdateBy)
            .set(updateTime).equalToWhenPresent(record::getUpdateTime)
            .set(dele).equalToWhenPresent(record::getDele)
            .set(remake).equalToWhenPresent(record::getRemake)
            .set(spare1).equalToWhenPresent(record::getSpare1)
            .set(value).equalToWhenPresent(record::getValue)
            .set(label).equalToWhenPresent(record::getLabel)
            .set(type).equalToWhenPresent(record::getType)
            .set(description).equalToWhenPresent(record::getDescription)
            .set(sort).equalToWhenPresent(record::getSort)
            .where(id, isEqualTo(record::getId))
        );
    }
}