package com.twj.spirngbasics.user.service;

import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.manage.RedisManage;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import com.twj.spirngbasics.user.dto.UserRoleDto;
import com.twj.spirngbasics.user.entity.UserResource;
import com.twj.spirngbasics.user.entity.UserRole;
import com.twj.spirngbasics.user.entity.UserRoleResource;
import com.twj.spirngbasics.user.entity.UserRoleUser;
import com.twj.spirngbasics.user.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private UserResourceMapper userResourceMapper;

    public UserRoleDto findById(String id) {
        ValidatorUtils.require(id, "参数不能为空");
        return CopyUtils.copy(userRoleMapper.selectByPrimaryKey(id).get(), UserRoleDto.class);
    }

    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SelectStatementProvider selectStatement = SqlBuilder.select(UserRoleMapper.selectList)
                .from(UserRoleDynamicSqlSupport.userRole)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<UserRole> list = userRoleMapper.selectMany(selectStatement);
        PageInfo pageInfo = new PageInfo(list);
        pageDto.setTotal(pageInfo.getTotal());
        pageDto.setList(CopyUtils.copyList(list, UserRoleDto.class));
    }

    /**
     * 新增
     */
    @Transactional
    public void insert(UserRole userRole) {
        userRole.insert();
        userRoleMapper.insert(userRole);
        //保存角色资源
        saveResource(userRole);
        userRole.setCompanyId(null);
    }

    /**
     * 更新
     */
    @Transactional
    public void update(UserRole userRole) {
        userRole.update();
        //为了保证安全，后台创建的不允许修改companyid
        userRole.setCompanyId(null);
        userRoleMapper.updateByPrimaryKeySelective(userRole);
        saveResource(userRole);
        RedisManage.delRoleResource(userRole.getId());
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
    public void saveResource(UserRole role) {
        String roleId = role.getId();
        if (role.getResourceIds() == null || role.getResourceIds().size() == 0) {
            return;
        }
        List<String> resourceIds = role.getResourceIds();
        // 清空库中所有的当前角色下的记录
        DeleteStatementProvider deleteStatement = SqlBuilder.deleteFrom(UserRoleResourceDynamicSqlSupport.userRoleResource)
                .where(UserRoleResourceDynamicSqlSupport.roleId, SqlBuilder.isEqualTo(roleId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        userRoleResourceMapper.delete(deleteStatement);


        // 保存角色资源
        List<UserRoleResource> insertList = new ArrayList<>();
        for (int i = 0; i < resourceIds.size(); i++) {
            UserRoleResource roleResource = new UserRoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resourceIds.get(i));
            roleResource.insert();
            insertList.add(roleResource);
        }

        userRoleResourceMapper.insertMultiple(insertList);
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
            roleUser.setId(IdUtil.simpleUUID());
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
        List<UserRoleUser> roleUserList = userRoleUserMapper.selectMany(selectStatement);

        List<String> userIdList = new ArrayList<>();
        for (int i = 0, l = roleUserList.size(); i < l; i++) {
            userIdList.add(roleUserList.get(i).getUserId());
        }
        return userIdList;
    }


    public Map<String, Object> getUserResourceList(String roleId) {
        SelectStatementProvider select = SqlBuilder.select(userResourceMapper.selectList)
                .from(UserResourceDynamicSqlSupport.userResource)
                .where(UserResourceDynamicSqlSupport.id, SqlBuilder.isIn(
                        SqlBuilder.select(UserRoleResourceDynamicSqlSupport.resourceId)
                                .from(UserRoleResourceDynamicSqlSupport.userRoleResource)
                                .where(UserRoleResourceDynamicSqlSupport.roleId, SqlBuilder.isEqualTo(roleId)
                                )))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<UserResource> resources = userResourceMapper.selectMany(select);
        Map<String, Object> resourceMap = new HashMap<>();
        for (UserResource resource : resources) {
            if (!StringUtils.isEmpty(resource.getRequest())) {
                resourceMap.put(resource.getRequest(), userResourceMapper.selectByPrimaryKey(resource.getParent()).get().getName() + "-" + resource.getName());
            }
        }
        return resourceMap;
    }
}
