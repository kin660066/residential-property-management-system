package com.sontan.rpms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sontan.rpms.entity.PaymentItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * (PaymentItem)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-21 11:28:27
 */
@Mapper
@Repository
public interface PaymentItemDao extends BaseMapper<PaymentItem> {

}