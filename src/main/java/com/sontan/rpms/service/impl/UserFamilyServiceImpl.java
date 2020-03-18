package com.sontan.rpms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sontan.rpms.dao.UserFamilyMapper;
import com.sontan.rpms.entity.UserFamily;
import com.sontan.rpms.service.UserFamilyService;
import org.springframework.stereotype.Service;

@Service
public class UserFamilyServiceImpl extends ServiceImpl<UserFamilyMapper, UserFamily> implements UserFamilyService {
}
