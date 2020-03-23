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
 * (Announcement)表实体类
 *
 * @author makejava
 * @since 2020-03-23 09:47:33
 */
@Data
@TableName("announcement")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Announcement extends Model<Announcement> {
    
    private Integer id;
    
    private String title;
    
    private String content;
    
    private Date publishtime;
    //1-高优先级 0-低优先级
    private Integer priority;
    //0-未删 1-已删
    private Integer delflag;



}