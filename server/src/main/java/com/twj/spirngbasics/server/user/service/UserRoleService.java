package com.twj.spirngbasics.server.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.user.dto.UserRoleDto;
import com.twj.spirngbasics.server.user.entity.*;
import com.twj.spirngbasics.server.user.mapper.UserRoleMapper;
import com.twj.spirngbasics.server.user.mapper.UserRoleResourceMapper;
import com.twj.spirngbasics.server.user.mapper.UserRoleUserMapper;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.UuidUtils;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @作者: Jun
 * @创建时间: 2021-01-05 17:27:50
 * @Version 1.0
 * @描述: 角色
 */
@Service
public class UserRoleService {

    private static final String BASIC = "0";
    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private UserRoleResourceMapper userRoleResourceMapper;

    @Resource
    private UserRoleUserMapper userRoleUserMapper;

    public UserRole findById(String id) {
        ValidatorUtils.require(id, "参数不能为空");
        return userRoleMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据公司id查询 当前公司有哪些角色(包括基础角色)
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UserRoleExample roleExample = new UserRoleExample();
        try {
            //先查询根据公司id查询
            roleExample.createCriteria()
                    .andCompanyIdEqualTo(pageDto.getList().get(0).toString());
            //OR 基本角色信息 公司id为0即为基本角色
            roleExample.or(roleExample.createCriteria()
                    .andCompanyIdEqualTo(BASIC));
        } catch (Exception e) {
            return;
        }
        List<UserRole> roleList = userRoleMapper.selectByExample(roleExample);
        PageInfo<UserRole> pageInfo = new PageInfo<>(roleList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserRoleDto> roleDtoList = CopyUtils.copyList(roleList, UserRoleDto.class);
        pageDto.setList(roleDtoList);
    }


    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(UserRole userRole) throws Exception {
        // 保存校验
        ValidatorUtils.require(userRole.getName(), "角色");
        ValidatorUtils.length(userRole.getName(), "角色:Length overrun", 50);
        ValidatorUtils.require(userRole.getDesc(), "描述");
        ValidatorUtils.length(userRole.getDesc(), "描述:Length overrun", 100);
        ValidatorUtils.require(userRole.getCompanyId(), "公司自定义角色");
        ValidatorUtils.length(userRole.getCompanyId(), "公司自定义角色:Length overrun", 32);
        ValidatorUtils.length(userRole.getUpdateBy(), "更新人:Length overrun", 32);
        if (StringUtils.isEmpty(userRole.getId())) {
            this.insert(userRole);
        } else {
            this.update(userRole);
        }
    }

    /**
     * 新增
     */
    private void insert(UserRole userRole) {
        userRole.insert();
        userRoleMapper.insert(userRole);
    }

    /**
     * 更新
     */
    private void update(UserRole userRole) {
        userRole.update();
        userRoleMapper.updateByPrimaryKeySelective(userRole);
    }

    /**
     * 获取基础角色
     *
     * @return
     */
    public List<UserRoleDto> basicRole() {
        UserRoleExample roleExample = new UserRoleExample();
        try {
            //基本角色信息 公司id为0即为基本角色
            roleExample.createCriteria()
                    .andCompanyIdEqualTo(BASIC);
        } catch (Exception e) {
            return null;
        }
        List<UserRole> roleList = userRoleMapper.selectByExample(roleExample);
        List<UserRoleDto> roleDtoList = CopyUtils.copyList(roleList, UserRoleDto.class);
        return roleDtoList;
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(UserRoleDto roleDto) {
        UserRole role = CopyUtils.copy(roleDto, UserRole.class);
        if (StringUtils.isEmpty(roleDto.getId())) {
            role.setId(UuidUtils.getUuid8());
            roleDto.setId(role.getId());
            this.insert(role);
        } else {
            this.update(role);
        }
    }


    /**
     * 删除
     */
    public boolean delete(String id) {
        UserRoleExample roleExample = new UserRoleExample();
        roleExample.createCriteria()
                .andCompanyIdEqualTo(BASIC)
                .andIdEqualTo(id);
        if (userRoleMapper.countByExample(roleExample) <= 0) {
            userRoleMapper.deleteByPrimaryKey(id);
            return true;
        }
        return false;
    }


    /**
     * 按角色保存资源
     */
    public void saveResource(UserRoleDto roleDto) {
        String roleId = roleDto.getId();
        List<String> resourceIds = roleDto.getResourceIds();
        // 清空库中所有的当前角色下的记录
        UserRoleResourceExample example = new UserRoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        userRoleResourceMapper.deleteByExample(example);

        // 保存角色资源
        List<UserRoleResource> insertList = new ArrayList<>();
        for (int i = 0; i < resourceIds.size(); i++) {
            UserRoleResource roleResource = new UserRoleResource();
            roleResource.setId(UuidUtils.getUuid8());
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(resourceIds.get(i));
            insertList.add(roleResource);
        }
        userRoleResourceMapper.insertList(insertList);
    }

    /**
     * 按角色加载资源
     *
     * @param roleId
     */
    public List<String> listResource(String roleId) {
        UserRoleResourceExample example = new UserRoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        example.setOrderByClause("resource_id asc");
        List<UserRoleResource> roleResourceList = userRoleResourceMapper.selectByExample(example);
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
        UserRoleUserExample example = new UserRoleUserExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        userRoleUserMapper.deleteByExample(example);

        // 保存角色用户
        for (int i = 0; i < userIdList.size(); i++) {
            UserRoleUser roleUser = new UserRoleUser();
            roleUser.setId(UuidUtils.getUuid8());
            roleUser.setRoleId(roleId);
            roleUser.setUserId(userIdList.get(i));
            userRoleUserMapper.insert(roleUser);
        }
    }

    /**
     * 按角色加载用户
     *
     * @param roleId
     */
    public List<String> listUser(String roleId) {
        UserRoleUserExample example = new UserRoleUserExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        List<UserRoleUser> roleUserList = userRoleUserMapper.selectByExample(example);
        List<String> userIdList = new ArrayList<>();
        for (int i = 0, l = roleUserList.size(); i < l; i++) {
            userIdList.add(roleUserList.get(i).getUserId());
        }
        return userIdList;
    }


}
