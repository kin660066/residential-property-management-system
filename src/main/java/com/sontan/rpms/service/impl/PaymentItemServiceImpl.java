package com.sontan.rpms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sontan.rpms.dao.PaymentItemDao;
import com.sontan.rpms.entity.PaymentItem;
import com.sontan.rpms.service.PaymentItemService;
import org.springframework.stereotype.Service;

/**
 * (PaymentItem)表服务实现类
 *
 * @author makejava
 * @since 2020-03-21 11:28:27
 */
@Service
public class PaymentItemServiceImpl extends ServiceImpl<PaymentItemDao, PaymentItem> implements PaymentItemService {

}