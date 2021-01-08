package com.twj.spirngbasics.server.user.service;

import com.twj.spirngbasics.server.user.entity.UserRoleResource;
import com.twj.spirngbasics.server.user.entity.UserRoleResourceExample;
import com.twj.spirngbasics.server.user.dto.UserRoleResourceDto;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.user.mapper.UserRoleResourceMapper;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.annotation.Resource;
import java.util.List;

/**
* @作者: Jun
* @创建时间: 2021-01-05 17:27:51
* @Version 1.0
* @描述: 角色资源关联
*/
@Service
public class UserRoleResourceService {

    @Resource
    private UserRoleResourceMapper userRoleResourceMapper;


    /**
     * 列表查询
     */
    public void list(PageDto pageDto)  throws Exception{
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UserRoleResourceExample userRoleResourceExample = new UserRoleResourceExample();
        List<UserRoleResource> userRoleResourceList = userRoleResourceMapper.selectByExample(userRoleResourceExample);
        PageInfo<UserRoleResource> pageInfo = new PageInfo<>(userRoleResourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserRoleResourceDto> userRoleResourceDtoList = CopyUtils.copyList(userRoleResourceList, UserRoleResourceDto.class);
        pageDto.setList(userRoleResourceDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(UserRoleResource userRoleResource) throws Exception{
        // 保存校验
        ValidatorUtils.require(userRoleResource.getRoleId(), "角色");
        ValidatorUtils.length(userRoleResource.getRoleId(), "角色:Length overrun", 32);
        ValidatorUtils.require(userRoleResource.getResourceId(), "资源");
        ValidatorUtils.length(userRoleResource.getResourceId(), "资源:Length overrun", 32);
        ValidatorUtils.length(userRoleResource.getUpdateBy(), "更新人:Length overrun", 32);
        if (StringUtils.isEmpty(userRoleResource.getId())) {
            this.insert(userRoleResource);
        } else {
            this.update(userRoleResource);
        }
    }

    /**
     * 新增
     */
    private void insert(UserRoleResource userRoleResource) {
        userRoleResource.insert();
        userRoleResourceMapper.insert(userRoleResource);
    }

    /**
     * 更新
     */
    private void update(UserRoleResource userRoleResource) {
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
