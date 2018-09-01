package com.jqh.SpringShiroDemo.mapper;

import com.jqh.SpringShiroDemo.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findByUsername(@Param("username") String username);
}
