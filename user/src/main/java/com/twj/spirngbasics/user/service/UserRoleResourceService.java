package com.twj.spirngbasics.user.service;

import com.github.pagehelper.PageHelper;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.user.dto.UserRoleResourceDto;
import com.twj.spirngbasics.user.entity.UserRoleResource;
import com.twj.spirngbasics.user.mapper.UserRoleResourceDynamicSqlSupport;
import com.twj.spirngbasics.user.mapper.UserRoleResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 角色资源关联
 */
@Service
@Slf4j
public class UserRoleResourceService {

    @Resource
    private UserRoleResourceMapper userRoleResourceMapper;


    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SelectStatementProvider selectStatement = SqlBuilder.select(userRoleResourceMapper.selectList)
                .from(UserRoleResourceDynamicSqlSupport.userRoleResource)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<UserRoleResourceDto> userRoleResourceDtoList = CopyUtils.copyList(userRoleResourceMapper.selectMany(selectStatement), UserRoleResourceDto.class);
        pageDto.setTotal(userRoleResourceDtoList.size());
        pageDto.setList(userRoleResourceDtoList);
    }

    /**
     * 新增
     */
    public void insert(UserRoleResource userRoleResource) {
        userRoleResource.insert();
        userRoleResourceMapper.insert(userRoleResource);
    }

    /**
     * 更新
     */
    public void update(UserRoleResource userRoleResource) {
        userRoleResource.update();
        userRoleResourceMapper.updateByPrimaryKeySelective(userRoleResource);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        userRoleResourceMapper.deleteByPrimaryKey(id);
    }
}
