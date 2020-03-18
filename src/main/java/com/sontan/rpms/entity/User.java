package com.sontan.rpms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.security.DenyAll;
import java.io.Serializable;
import java.util.Date;
@TableName("user_info")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User implements Serializable {
    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer id;
    private String account;
    private String password;
    private String username;
    private Integer sex;
    private String phone;
    private String address;
    private Integer age;
    private Integer type;
    private String card;
    private String createby;
    private Date createtime;
}
