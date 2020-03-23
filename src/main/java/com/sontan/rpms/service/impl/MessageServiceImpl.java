package com.sontan.rpms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sontan.rpms.dao.MessageDao;
import com.sontan.rpms.entity.Message;
import com.sontan.rpms.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * (Message)表服务实现类
 *
 * @author makejava
 * @since 2020-03-23 09:47:33
 */
@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, Message> implements MessageService {

}