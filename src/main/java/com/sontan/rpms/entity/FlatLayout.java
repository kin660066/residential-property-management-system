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
 * (UserFamily)表实体类
 *
 * @author makejava
 * @since 2020-03-13 09:06:47
 */

@Data
@TableName("flat_layout")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class FlatLayout extends Model<FlatLayout> implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String layout;
}

