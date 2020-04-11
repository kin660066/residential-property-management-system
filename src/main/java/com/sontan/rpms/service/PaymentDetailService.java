package com.sontan.rpms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sontan.rpms.entity.PaymentDetail;
import com.sontan.rpms.vo.FlatVo;
import com.sontan.rpms.vo.PaymentVo;
import org.springframework.stereotype.Service;

/**
 * (PaymentDetail)表服务接口
 *
 * @author makejava
 * @since 2020-03-27 10:04:45
 */
@Service
public interface PaymentDetailService extends IService<PaymentDetail> {
    Page<PaymentVo> getFlatVo2(Page<PaymentVo> page,String username,Integer month);
    Page<PaymentVo> getFlatVo3(Page<PaymentVo> page,String username);
    Page<PaymentVo> getFlatVo4(Page<PaymentVo> page,Integer month);
    Page<PaymentVo> getFlatVo(Page<PaymentVo> page);
    Page<PaymentVo> getFlatVo1(Page<PaymentVo> page,Integer id);
}