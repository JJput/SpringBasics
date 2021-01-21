
import com.twj.spirngbasics.business.BusinessApplication;
import com.twj.spirngbasics.server.entity.SysDict;
import com.twj.spirngbasics.server.mapper.SysDictDynamicSqlSupport;
import com.twj.spirngbasics.server.mapper.SysDictMapper;

import static com.twj.spirngbasics.server.mapper.SysDictDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.twj.spirngbasics.user.entity.UserResource;
import com.twj.spirngbasics.user.mapper.UserResourceDynamicSqlSupport;
import com.twj.spirngbasics.user.mapper.UserResourceMapper;
import com.twj.spirngbasics.user.mapper.UserRoleResourceDynamicSqlSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;

import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

import java.util.List;

/**
 * @作者: JJ
 * @创建时间: 2020/11/20
 * @Version 1.0
 * @描述: DynamicSql语法测试及示例
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BusinessApplication.class)
public class DynamicSqlTest {

    @Resource
    private SysDictMapper sysDictMapper;

    /*****  查  *****/
    /**
     * 查询指定列
     */
    @Test
    public void selectDemo1() {
        SelectStatementProvider selectStatement = SqlBuilder.select(id, label, value)
                .from(SysDictDynamicSqlSupport.sysDict)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysDict> list = sysDictMapper.selectMany(selectStatement);
    }

    /**
     * 查询所有列
     */
    @Test
    public void selectDemo2() {
        SelectStatementProvider selectStatement = SqlBuilder.select(SysDictMapper.selectList)
                .from(SysDictDynamicSqlSupport.sysDict)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysDict> list = sysDictMapper.selectMany(selectStatement);
    }

    /**
     * 条件查询
     */
    @Test
    public void selectDemo3() {
        SelectStatementProvider selectStatement = SqlBuilder.select(SysDictMapper.selectList)
                .from(SysDictDynamicSqlSupport.sysDict)
                .where(label, isIn("女", "男"))
                .orderBy(value)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysDict> list = sysDictMapper.selectMany(selectStatement);
    }

    @Resource
    private UserResourceMapper userResourceMapper;

    /**
     * 子查询
     */
    @Test
    public void selectDemo4() {
        SelectStatementProvider selectStatement = SqlBuilder.select(userResourceMapper.selectList)
                .from(UserResourceDynamicSqlSupport.userResource)
                .where(UserResourceDynamicSqlSupport.id, isIn(
                        select(UserRoleResourceDynamicSqlSupport.resourceId)
                                .from(UserRoleResourceDynamicSqlSupport.userRoleResource)
                                .where(UserRoleResourceDynamicSqlSupport.roleId, isEqualTo("1"))))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<UserResource> list = userResourceMapper.selectMany(selectStatement);
    }

    public void selectDemo5() {
//        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder builder = SqlBuilder.select(SysDictMapper.selectList)
//                .from(SysDictDynamicSqlSupport.sysDict)
//                .where();
//
//        if (x)
//            builder.where(label, isIn("女", "男"));
//
//        if (y)
//            builder.where(row,...);
//
//        SelectStatementProvider selectStatement = builder.build().render(RenderingStrategies.MYBATIS3);
//
//        List<SysDict> list = sysDictMapper.selectMany(selectStatement);
    }


    /*****  增  *****/
    /**
     * 新增一条
     */
    @Test
    public void insertOne() {
        SysDict sysDict = new SysDict();
        sysDict.setLabel("测试");
        sysDict.setValue("0");
        sysDict.setType("test");
        sysDict.setSort(0);
        sysDict.setDescription("测试");
        sysDict.insert("SYSTEM");
        int row = sysDictMapper.insert(sysDict);
        System.out.println("成功插入条数：" + row);
    }

    /**
     * 批量新增
     */
    @Test
    public void insertList() {
        List<SysDict> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            SysDict sysDict = new SysDict();
            sysDict.setLabel("测试");
            sysDict.setValue(String.valueOf(i));
            sysDict.setType("test");
            sysDict.setSort(i);
            sysDict.setDescription("测试");
            sysDict.insert("SYSTEM");
            list.add(sysDict);
        }
        MultiRowInsertStatementProvider<SysDict> multiRowInsert = SqlBuilder.insertMultiple(list)
                .into(SysDictDynamicSqlSupport.sysDict)
                .map(id).toProperty("id")
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
                .build()
                .render(RenderingStrategies.MYBATIS3);

        sysDictMapper.insertMultiple(multiRowInsert);
    }

    /**
     * 删除
     */
    @Test
    public void del() {
        //根据主键删除
        sysDictMapper.deleteByPrimaryKey("");

        //条件删除
        DeleteStatementProvider deleteStatement = deleteFrom(SysDictDynamicSqlSupport.sysDict)
                .where(SysDictDynamicSqlSupport.type, isEqualTo("test"))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        sysDictMapper.delete(deleteStatement);

    }

    /**
     * 更新
     */
    @Test
    public void up() {
        SysDict sysDict = new SysDict();
        //根据主键对所有属性进行更新
        sysDictMapper.updateByPrimaryKey(sysDict);
        //根据主键对不为null的属性进行更新
        sysDictMapper.updateByPrimaryKeySelective(sysDict);

        UpdateStatementProvider updateStatement = update(SysDictDynamicSqlSupport.sysDict)
                .set(remake).equalToNull()
                .where(type, isEqualTo("test"))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        int rows = sysDictMapper.update(updateStatement);
        System.out.println("成功更新条数：" + rows);
    }


}
