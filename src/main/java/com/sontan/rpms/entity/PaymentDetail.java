package com.sontan.rpms.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (PaymentDetail)表实体类
 *
 * @author makejava
 * @since 2020-03-27 10:04:45
 */
@Data
@TableName("payment_detail")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PaymentDetail extends Model<PaymentDetail> {
    
    private Integer id;
    
    private Integer months;
    
    private Integer pid;
    
    private Double money;
    
    private String createby;
    
    private Date createtime;
    
    private Integer state;
    
    private Integer ownerid;

    private Date paytime;



}