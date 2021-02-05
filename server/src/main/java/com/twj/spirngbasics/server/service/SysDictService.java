package com.twj.spirngbasics.server.service;

import com.github.pagehelper.PageHelper;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.SysDictDto;
import com.twj.spirngbasics.server.entity.SysDict;
import com.twj.spirngbasics.server.mapper.SysDictDynamicSqlSupport;
import com.twj.spirngbasics.server.mapper.SysDictMapper;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 15:51:40
 * @Version 1.0
 * @描述: 字典表
 */
@Service
@Slf4j
public class SysDictService {

    @Resource
    private SysDictMapper sysDictMapper;

    public SysDictDto findById(String id) {
        ValidatorUtils.require(id, "参数不能为空");
        return CopyUtils.copy(sysDictMapper.selectByPrimaryKey(id).get(), SysDictDto.class);
    }

    /**
     * 列表查询
     */
    public void list(PageDto<SysDictDto> pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SelectStatementProvider selectStatement = SqlBuilder.select(sysDictMapper.selectList)
                .from(SysDictDynamicSqlSupport.sysDict)
                .where(SysDictDynamicSqlSupport.type, SqlBuilder.isEqualToWhenPresent(pageDto.getData().getType()))
                .orderBy(SysDictDynamicSqlSupport.sort)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<SysDictDto> sysDictDtoList = CopyUtils.copyList(sysDictMapper.selectMany(selectStatement), SysDictDto.class);
        pageDto.setTotal(sysDictDtoList.size());
        pageDto.setList(sysDictDtoList);
    }

    /**
     * 新增
     */
    public void insert(SysDict sysDict) {
        sysDict.insert();
        sysDictMapper.insert(sysDict);
    }

    /**
     * 更新
     */
    public void update(SysDict sysDict) {
        sysDict.update();
        sysDictMapper.updateByPrimaryKeySelective(sysDict);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        sysDictMapper.deleteByPrimaryKey(id);
    }
}
