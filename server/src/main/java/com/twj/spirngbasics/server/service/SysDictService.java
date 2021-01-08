package com.twj.spirngbasics.server.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.dto.SysDictDto;
import com.twj.spirngbasics.server.entity.SysDict;
import com.twj.spirngbasics.server.entity.SysDictExample;
import com.twj.spirngbasics.server.mapper.SysDictMapper;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-06 15:35:31
 * @Version 1.0
 * @描述: 字典表
 */
@Service
public class SysDictService {

    private static final Logger LOG = LoggerFactory.getLogger(SysDictService.class);

    @Resource
    private SysDictMapper sysDictMapper;

    public SysDict findById(String id) {
        ValidatorUtils.require(id, "参数不能为空");
        return sysDictMapper.selectByPrimaryKey(id);
    }

    /**
     * 列表查询
     */
    public void list(PageDto<SysDictDto> pageDto) throws Exception {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SysDictExample sysDictExample = new SysDictExample();
        sysDictExample.createCriteria().andTypeEqualTo(pageDto.getData().getType());
        sysDictExample.setOrderByClause("sort asc");
        List<SysDict> sysDictList = sysDictMapper.selectByExample(sysDictExample);
        PageInfo<SysDict> pageInfo = new PageInfo<>(sysDictList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SysDictDto> sysDictDtoList = CopyUtils.copyList(sysDictList, SysDictDto.class);
        pageDto.setList(sysDictDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(SysDict sysDict) throws Exception {
        // 保存校验
        ValidatorUtils.length(sysDict.getUpdateBy(), "更新人:Length overrun", 32);
        ValidatorUtils.length(sysDict.getRemake(), "备注:Length overrun", 512);
        ValidatorUtils.length(sysDict.getSpare1(), "备用1:Length overrun", 64);
        ValidatorUtils.require(sysDict.getValue(), "数据值");
        ValidatorUtils.length(sysDict.getValue(), "数据值:Length overrun", 128);
        ValidatorUtils.require(sysDict.getLabel(), "标签名");
        ValidatorUtils.length(sysDict.getLabel(), "标签名:Length overrun", 128);
        ValidatorUtils.require(sysDict.getType(), "类型");
        ValidatorUtils.length(sysDict.getType(), "类型:Length overrun", 128);
        ValidatorUtils.require(sysDict.getDescription(), "描述");
        ValidatorUtils.length(sysDict.getDescription(), "描述:Length overrun", 128);
        ValidatorUtils.require(sysDict.getSort(), "排序（升序）");
        ValidatorUtils.length(sysDict.getSort(), "排序（升序）:Length overrun", 32);
        if (StringUtils.isEmpty(sysDict.getId())) {
            this.insert(sysDict);
        } else {
            this.update(sysDict);
        }
    }

    /**
     * 新增
     */
    private void insert(SysDict sysDict) {
        sysDict.insert();
        sysDictMapper.insert(sysDict);
    }

    /**
     * 更新
     */
    private void update(SysDict sysDict) {
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
