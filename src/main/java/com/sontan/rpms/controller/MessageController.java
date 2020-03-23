package com.sontan.rpms.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.entity.Message;
import com.sontan.rpms.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2020-03-23 09:47:33
 */
@RestController
@RequestMapping("message")
public class MessageController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MessageService messageService;

}