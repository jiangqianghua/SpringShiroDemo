package com.jqh.SpringShiroDemo.service;

import com.jqh.SpringShiroDemo.model.User;

public interface UserService {

    User findByUsername(String username);
}
