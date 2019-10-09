package com.kavito.mybatisgenerator.mapper;

import com.kavito.mybatisgenerator.pojo.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int deleteByPrimaryKey(@Param("uid") Integer uid, @Param("rid") Integer rid);

    int insert(UserRole record);

    List<UserRole> selectAll();
}