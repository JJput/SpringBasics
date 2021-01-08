package com.twj.spirngbasics.server.user.mapper;

import com.twj.spirngbasics.server.user.entity.UserRoleUser;
import com.twj.spirngbasics.server.user.entity.UserRoleUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleUserMapper {
    long countByExample(UserRoleUserExample example);

    int deleteByExample(UserRoleUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserRoleUser record);

    int insertSelective(UserRoleUser record);

    List<UserRoleUser> selectByExample(UserRoleUserExample example);

    UserRoleUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserRoleUser record, @Param("example") UserRoleUserExample example);

    int updateByExample(@Param("record") UserRoleUser record, @Param("example") UserRoleUserExample example);

    int updateByPrimaryKeySelective(UserRoleUser record);

    int updateByPrimaryKey(UserRoleUser record);
}
