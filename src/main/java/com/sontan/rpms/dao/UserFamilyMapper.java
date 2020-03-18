package com.sontan.rpms.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.entity.UserFamily;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserFamilyMapper extends BaseMapper<UserFamily> {

}
