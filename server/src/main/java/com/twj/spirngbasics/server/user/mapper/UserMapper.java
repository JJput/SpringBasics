package com.twj.spirngbasics.server.user.mapper;


import com.twj.spirngbasics.server.user.dto.UserResourceDto;
import com.twj.spirngbasics.server.user.entity.User;
import com.twj.spirngbasics.server.user.entity.UserExample;
import com.twj.spirngbasics.server.user.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserResourceDto> findResources(String id);

    UserRole selectRole(String id);

    List<Map> selectListAndRoleId(String cid);
}
