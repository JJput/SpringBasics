package com.twj.spirngbasics.user.service;

import com.github.pagehelper.PageHelper;
import com.twj.spirngbasics.server.dto.PageDto;
import com.twj.spirngbasics.server.util.CopyUtils;
import com.twj.spirngbasics.server.util.ValidatorUtils;
import com.twj.spirngbasics.user.dto.UserRoleUserDto;
import com.twj.spirngbasics.user.entity.UserRoleUser;
import com.twj.spirngbasics.user.mapper.UserRoleUserDynamicSqlSupport;
import com.twj.spirngbasics.user.mapper.UserRoleUserMapper;
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
 * @描述: 角色用户关联
 */
@Service
@Slf4j
public class UserRoleUserService {

    @Resource
    private UserRoleUserMapper userRoleUserMapper;

    public UserRoleUserDto findById(String id) {
        ValidatorUtils.require(id, "参数不能为空");
        return CopyUtils.copy(userRoleUserMapper.selectByPrimaryKey(id),UserRoleUserDto.class);
    }

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SelectStatementProvider selectStatement = SqlBuilder.select(userRoleUserMapper.selectList)
                .from(UserRoleUserDynamicSqlSupport.userRoleUser)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<UserRoleUserDto> userRoleUserDtoList = CopyUtils.copyList(userRoleUserMapper.selectMany(selectStatement), UserRoleUserDto.class);
        pageDto.setTotal(userRoleUserDtoList.size());
        pageDto.setList(userRoleUserDtoList);
    }

    /**
     * 新增
     */
    public void insert(UserRoleUser userRoleUser) {
        userRoleUser.insert();
        userRoleUserMapper.insert(userRoleUser);
    }

    /**
     * 更新
     */
    public void update(UserRoleUser userRoleUser) {
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
