package com.sontan.rpms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sontan.rpms.entity.PaymentItem;

import java.util.List;

/**
 * (PaymentItem)表服务接口
 *
 * @author makejava
 * @since 2020-03-21 11:28:27
 */
public interface PaymentItemService extends IService<PaymentItem> {
    List<String> getPaymentItem();
}