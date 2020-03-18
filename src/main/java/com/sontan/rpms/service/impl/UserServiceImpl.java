package com.sontan.rpms.service.impl;

import com.sontan.rpms.dao.UserMapper;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.service.UserService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;




@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
