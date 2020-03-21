package com.sontan.rpms.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Parking)表实体类
 *
 * @author makejava
 * @since 2020-03-20 08:50:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("parking")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Parking extends Model<Parking> implements Serializable{
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer ownerid;
    private Integer block;
    private Date starttime;
    private Date endtime;
    private String carno;
    private Integer state;
    private String no;

}