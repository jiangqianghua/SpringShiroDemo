package com.jqh.SpringShiroDemo.service;

import com.jqh.SpringShiroDemo.mapper.UserMapper;
import com.jqh.SpringShiroDemo.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper ;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
