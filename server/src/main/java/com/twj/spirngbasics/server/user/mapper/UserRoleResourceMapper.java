package com.twj.spirngbasics.server.user.mapper;

import com.twj.spirngbasics.server.user.entity.UserRoleResource;
import com.twj.spirngbasics.server.user.entity.UserRoleResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleResourceMapper {
    long countByExample(UserRoleResourceExample example);

    int deleteByExample(UserRoleResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserRoleResource record);

    int insertSelective(UserRoleResource record);

    List<UserRoleResource> selectByExample(UserRoleResourceExample example);

    UserRoleResource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserRoleResource record, @Param("example") UserRoleResourceExample example);

    int updateByExample(@Param("record") UserRoleResource record, @Param("example") UserRoleResourceExample example);

    int updateByPrimaryKeySelective(UserRoleResource record);

    int updateByPrimaryKey(UserRoleResource record);

    void insertList(List<UserRoleResource> insertList);
}
