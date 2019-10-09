package com.kavito.mybatisgenerator.mapper;

import com.kavito.mybatisgenerator.pojo.User;
import java.util.List;

public interface UserMapper {
    int insert(User record);

    List<User> selectAll();
}