package com.sontan.rpms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sontan.rpms.entity.PublicFacilities;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (PublicFacilities)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-20 08:45:11
 */
@Mapper
@Repository
public interface PublicFacilitiesDao extends BaseMapper<PublicFacilities> {

}