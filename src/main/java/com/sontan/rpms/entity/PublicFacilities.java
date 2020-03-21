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
 * (PublicFacilities)表实体类
 *
 * @author makejava
 * @since 2020-03-20 08:45:11
 */
@Data
@TableName("public_facilities")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PublicFacilities extends Model<PublicFacilities> {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    
    private String facility;
    
    private String area;
    
    private Integer state;



}