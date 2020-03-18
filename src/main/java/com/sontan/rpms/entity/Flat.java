package com.sontan.rpms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("flat_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Flat implements Serializable {
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;
    private Integer block;
    private Integer unit;
    private Integer room;
    private Integer floor;
    private String no;
    private BigDecimal space;
    private Integer layoutid;
    private Integer state;
    private Integer ownerid;
}
