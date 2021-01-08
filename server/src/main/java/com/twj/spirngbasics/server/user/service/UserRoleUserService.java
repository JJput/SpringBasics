package com.twj.spirngbasics.server.user.service;

import com.twj.spirngbasics.server.user.entity.UserRoleUser;
import com.twj.spirngbasics.server.user.entity.UserRoleUserExample;
import com.twj.spirngbasics.server.user.dto.UserRoleUserDto;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.user.mapper.UserRoleUserMapper;
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
* @描述: 角色用户关联
*/
@Service
public class UserRoleUserService {

    @Resource
    private UserRoleUserMapper userRoleUserMapper;

    public UserRoleUser findById(String id) {
        ValidatorUtils.require(id, "参数不能为空");
        return userRoleUserMapper.selectByPrimaryKey(id);
    }

    /**
     * 列表查询
     */
    public void list(PageDto pageDto)  throws Exception{
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UserRoleUserExample userRoleUserExample = new UserRoleUserExample();
        List<UserRoleUser> userRoleUserList = userRoleUserMapper.selectByExample(userRoleUserExample);
        PageInfo<UserRoleUser> pageInfo = new PageInfo<>(userRoleUserList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserRoleUserDto> userRoleUserDtoList = CopyUtils.copyList(userRoleUserList, UserRoleUserDto.class);
        pageDto.setList(userRoleUserDtoList);
    }

    /**
     * 保存，id有值时更新，无值时新增
     */
    public void save(UserRoleUser userRoleUser) throws Exception{
        // 保存校验
        ValidatorUtils.require(userRoleUser.getRoleId(), "角色");
        ValidatorUtils.length(userRoleUser.getRoleId(), "角色:Length overrun", 32);
        ValidatorUtils.require(userRoleUser.getUserId(), "用户");
        ValidatorUtils.length(userRoleUser.getUserId(), "用户:Length overrun", 32);
        ValidatorUtils.length(userRoleUser.getUpdateBy(), "更新人:Length overrun", 32);
        if (StringUtils.isEmpty(userRoleUser.getId())) {
            this.insert(userRoleUser);
        } else {
            this.update(userRoleUser);
        }
    }

    /**
     * 新增
     */
    private void insert(UserRoleUser userRoleUser) {
        userRoleUser.insert();
        userRoleUserMapper.insert(userRoleUser);
    }

    /**
     * 更新
     */
    private void update(UserRoleUser userRoleUser) {
        userRoleUser.update();
        userRoleUserMapper.updateByPrimaryKeySelective(userRoleUser);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        userRoleUserMapper.deleteByPrimaryKey(id);
    }
}
