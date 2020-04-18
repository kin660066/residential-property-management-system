package com.sontan.rpms.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sontan.rpms.entity.PaymentDetail;
import com.sontan.rpms.vo.PaymentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (PaymentDetail)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 10:04:45
 */
@Mapper
@Repository
public interface PaymentDetailDao extends BaseMapper<PaymentDetail> {
    @Select("SELECT ui.username,pi.item,pd.* from payment_detail pd LEFT JOIN payment_item pi on pd.pid=pi.id LEFT JOIN user_info ui on pd.ownerid=ui.id")
    List<PaymentVo> getPaymentVo(IPage<PaymentVo> page);
    @Select("SELECT ui.username,pi.item,pd.* from payment_detail pd LEFT JOIN payment_item pi on pd.pid=pi.id LEFT JOIN user_info ui on pd.ownerid=ui.id where ui.id= #{id}")
    List<PaymentVo> getPaymentVo1(IPage<PaymentVo> page,Integer id);
    @Select("SELECT ui.username,pi.item,pd.* from payment_detail pd LEFT JOIN payment_item pi on pd.pid=pi.id LEFT JOIN user_info ui on pd.ownerid=ui.id where ui.username like '%${username}%' and pd.months=#{month} order by year desc,months desc")
    List<PaymentVo> getPaymentVo2(IPage<PaymentVo> page,String username,Integer month);
    @Select("SELECT ui.username,pi.item,pd.* from payment_detail pd LEFT JOIN payment_item pi on pd.pid=pi.id LEFT JOIN user_info ui on pd.ownerid=ui.id where ui.username like '%${username}%' order by year desc,months desc ")
    List<PaymentVo> getPaymentVo3(IPage<PaymentVo> page,String username);
    @Select("SELECT ui.username,pi.item,pd.* from payment_detail pd LEFT JOIN payment_item pi on pd.pid=pi.id LEFT JOIN user_info ui on pd.ownerid=ui.id where pd.months=#{month} order by year desc ,months desc")
    List<PaymentVo> getPaymentVo4(IPage<PaymentVo> page,Integer month);
    @Select("SELECT sum(money) from payment_detail where year = #{year} GROUP BY months")
    List<String> getStatistics(String year);
    @Select("SELECT DISTINCT months from payment_detail ORDER BY months asc")
    List<String> getMonths();
    @Select("SELECT SUM(money),months,pid from payment_detail where year=#{year} AND pid=#{pid} GROUP BY months ")
    List<String> statisticsKinds(String year,Integer pid);

}