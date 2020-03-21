package com.sontan.rpms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (PaymentItem)表实体类
 *
 * @author makejava
 * @since 2020-03-21 11:28:27
 */
@Data
@TableName("payment_item")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PaymentItem extends Model<PaymentItem> {

    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;
    
    private String item;



}