package com.sontan.rpms.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sontan.rpms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
