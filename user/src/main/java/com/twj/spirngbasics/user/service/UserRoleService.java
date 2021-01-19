package com.twj.spirngbasics.user.service;

import com.github.pagehelper.PageHelper;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.UuidUtils;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import com.twj.spirngbasics.user.dto.UserRoleDto;
import com.twj.spirngbasics.user.entity.UserRole;
import com.twj.spirngbasics.user.entity.UserRoleResource;
import com.twj.spirngbasics.user.entity.UserRoleUser;
import com.twj.spirngbasics.user.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-19 16:00:38
 * @Version 1.0
 * @描述: 角色
 */
@Service
@Slf4j
public class UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private UserRoleResourceMapper userRoleResourceMapper;

    @Resource
    private UserRoleUserMapper userRoleUserMapper;

    public UserRoleDto findById(String id) {
        ValidatorUtils.require(id, "参数不能为空");
        return CopyUtils.copy(userRoleMapper.selectByPrimaryKey(id), UserRoleDto.class);
    }

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SelectStatementProvider selectStatement = SqlBuilder.select(userRoleMapper.selectList)
                .from(UserRoleDynamicSqlSupport.userRole)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<UserRoleDto> userRoleDtoList = CopyUtils.copyList(userRoleMapper.selectMany(selectStatement), UserRoleDto.class);
        pageDto.setTotal(userRoleDtoList.size());
        pageDto.setList(userRoleDtoList);
    }

    /**
     * 新增
     */
    public void insert(UserRole userRole) {
        userRole.insert();
        userRoleMapper.insert(userRole);
    }

    /**
     * 更新
     */
    public void update(UserRole userRole) {
        userRole.update();
        userRoleMapper.updateByPrimaryKeySelective(userRole);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        userRoleMapper.deleteByPrimaryKey(id);
    }


    /**
     * 按角色保存资源
     */
    public void saveResource(UserRoleDto roleDto) {
        String roleId = roleDto.getId();
        List<String> resourceIds = roleDto.getResourceIds();
        // 清空库中所有的当前角色下的记录
        DeleteStatementProvider deleteStatement = SqlBuilder.deleteFrom(UserRoleResourceDynamicSqlSupport.userRoleResource)
                .where(UserRoleResourceDynamicSqlSupport.roleId, SqlBuilder.isNotEqualToWhenPresent(roleId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        userRoleMapper.delete(deleteStatement);


        // 保存角色资源
        List<UserRoleResource> insertList = new ArrayList<>();
        for (int i = 0; i < resourceIds.size(); i++) {
            UserRoleResource roleResource = new UserRoleResource();
            roleResource.setId(UuidUtils.getUuid8());
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resourceIds.get(i));
            insertList.add(roleResource);
        }

        MultiRowInsertStatementProvider<UserRoleResource> multiRowInsert = SqlBuilder.insertMultiple(insertList)
                .into(UserRoleResourceDynamicSqlSupport.userRoleResource)
                .build()
                .render(RenderingStrategies.MYBATIS3);

        userRoleResourceMapper.insertMultiple(multiRowInsert);
    }

    /**
     * 按角色加载资源
     *
     * @param roleId
     */
    public List<String> listResource(String roleId) {
        SelectStatementProvider selectStatement = SqlBuilder.select(userRoleResourceMapper.selectList)
                .from(UserRoleResourceDynamicSqlSupport.userRoleResource)
                .where(UserRoleResourceDynamicSqlSupport.roleId, SqlBuilder.isEqualToWhenPresent(roleId))
                .orderBy(UserRoleResourceDynamicSqlSupport.resourceId)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<UserRoleResource> roleResourceList = userRoleResourceMapper.selectMany(selectStatement);

        List<String> resourceIdList = new ArrayList<>();
        for (int i = 0, l = roleResourceList.size(); i < l; i++) {
            resourceIdList.add(roleResourceList.get(i).getResourceId());
        }
        return resourceIdList;
    }

    /**
     * 按角色保存用户
     */
    public void saveUser(UserRoleDto roleDto) {
        String roleId = roleDto.getId();
        List<String> userIdList = roleDto.getUserIds();
        // 清空库中所有的当前角色下的记录
        DeleteStatementProvider deleteStatement = SqlBuilder.deleteFrom(UserRoleResourceDynamicSqlSupport.userRoleResource)
                .where(UserRoleResourceDynamicSqlSupport.roleId, SqlBuilder.isNotEqualToWhenPresent(roleId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        userRoleMapper.delete(deleteStatement);

        // 保存角色用户
        for (int i = 0; i < userIdList.size(); i++) {
            UserRoleUser roleUser = new UserRoleUser();
            roleUser.setId(UuidUtils.getUuid8());
            roleUser.setRoleId(roleId);
            roleUser.setUserId(userIdList.get(i));
            roleUser.insert();
            userRoleUserMapper.insert(roleUser);
        }
    }

    /**
     * 按角色加载用户
     *
     * @param roleId
     */
    public List<String> listUser(String roleId) {
        SelectStatementProvider selectStatement = SqlBuilder.select(UserRoleUserMapper.selectList)
                .from(UserRoleUserDynamicSqlSupport.userRoleUser)
                .where(UserRoleUserDynamicSqlSupport.roleId, SqlBuilder.isEqualToWhenPresent(roleId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<UserRoleUser> roleUserList= userRoleUserMapper.selectMany(selectStatement);

        List<String> userIdList = new ArrayList<>();
        for (int i = 0, l = roleUserList.size(); i < l; i++) {
            userIdList.add(roleUserList.get(i).getUserId());
        }
        return userIdList;
    }

}
