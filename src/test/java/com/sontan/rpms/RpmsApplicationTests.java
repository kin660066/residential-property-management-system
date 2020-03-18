package com.sontan.rpms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.dao.UserMapper;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.service.FlatService;
import com.sontan.rpms.utils.CardUtil;
import com.sontan.rpms.utils.RoomNoUtil;
import com.sontan.rpms.vo.FlatVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RpmsApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    FlatService flatService;
    @Test
    void contextLoads() {
        System.out.println(userMapper.selectById(1));
    }
    @Test
    void test01(){
        System.out.println(RoomNoUtil.getRoomNo(1,2,3,4));
    }
    @Test
    void test02(){
        String card1 = "440681199710034218";
        String card2 = "342221870606259";
        CardUtil cardUtil = new CardUtil();
        System.out.println(CardUtil.getAge(card1));
        System.out.println(cardUtil.getAge(card2));
        }
}
