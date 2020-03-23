package com.sontan.rpms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sontan.rpms.entity.Announcement;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (Announcement)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-23 09:47:33
 */
@Mapper
@Repository
public interface AnnouncementDao extends BaseMapper<Announcement> {

}