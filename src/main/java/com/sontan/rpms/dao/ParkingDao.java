package com.sontan.rpms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sontan.rpms.entity.Parking;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Parking)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-20 08:50:20
 */
@Mapper
@Repository
public interface ParkingDao extends BaseMapper<Parking> {

}