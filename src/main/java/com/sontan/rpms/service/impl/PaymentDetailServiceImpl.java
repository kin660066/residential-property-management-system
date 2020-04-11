package com.sontan.rpms.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sontan.rpms.dao.PaymentDetailDao;
import com.sontan.rpms.entity.PaymentDetail;
import com.sontan.rpms.service.PaymentDetailService;
import com.sontan.rpms.vo.PaymentVo;
import org.springframework.stereotype.Service;

/**
 * (PaymentDetail)表服务实现类
 *
 * @author makejava
 * @since 2020-03-27 10:04:45
 */
@Service("paymentDetailService")
public class PaymentDetailServiceImpl extends ServiceImpl<PaymentDetailDao, PaymentDetail> implements PaymentDetailService {
    @Override
    public Page<PaymentVo> getFlatVo2(Page<PaymentVo> page, String username, Integer month) {
        return page.setRecords(this.baseMapper.getPaymentVo2(page,username,month));
    }

    @Override
    public Page<PaymentVo> getFlatVo(Page<PaymentVo> page) {
        return page.setRecords(this.baseMapper.getPaymentVo(page));
    }

    @Override
    public Page<PaymentVo> getFlatVo1(Page<PaymentVo> page, Integer id) {
        return page.setRecords(this.baseMapper.getPaymentVo1(page,id));
    }

    @Override
    public Page<PaymentVo> getFlatVo3(Page<PaymentVo> page, String username) {
        return page.setRecords(this.baseMapper.getPaymentVo3(page,username));
    }

    @Override
    public Page<PaymentVo> getFlatVo4(Page<PaymentVo> page, Integer month) {
        return page.setRecords(this.baseMapper.getPaymentVo4(page,month));
    }
}