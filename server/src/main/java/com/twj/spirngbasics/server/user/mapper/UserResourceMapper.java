package com.twj.spirngbasics.server.user.mapper;

import com.twj.spirngbasics.server.user.entity.UserResource;
import com.twj.spirngbasics.server.user.entity.UserResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserResourceMapper {
    long countByExample(UserResourceExample example);

    int deleteByExample(UserResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(UserResource record);

    int insertSelective(UserResource record);

    List<UserResource> selectByExample(UserResourceExample example);

    UserResource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") UserResource record, @Param("example") UserResourceExample example);

    int updateByExample(@Param("record") UserResource record, @Param("example") UserResourceExample example);

    int updateByPrimaryKeySelective(UserResource record);

    int updateByPrimaryKey(UserResource record);
}
