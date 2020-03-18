package com.sontan.rpms.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.entity.FlatLayout;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FlatLayoutMapper extends BaseMapper<FlatLayout> {

}
