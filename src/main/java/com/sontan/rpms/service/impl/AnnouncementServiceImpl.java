package com.sontan.rpms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sontan.rpms.dao.AnnouncementDao;
import com.sontan.rpms.entity.Announcement;
import com.sontan.rpms.service.AnnouncementService;
import org.springframework.stereotype.Service;

/**
 * (Announcement)表服务实现类
 *
 * @author makejava
 * @since 2020-03-23 09:47:33
 */
@Service("announcementService")
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementDao, Announcement> implements AnnouncementService {

}