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
 * (Message)表实体类
 *
 * @author makejava
 * @since 2020-03-23 09:47:33
 */
@Data
@TableName("message")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Message extends Model<Message> {
    
    private Integer id;
    
    private String title;
    
    private String content;
    //0-投诉 1-申请
    private Integer type;
    
    private String username;
    
    private Date createtime;
    //0-未处理 1-已处理
    private Integer flag;



}