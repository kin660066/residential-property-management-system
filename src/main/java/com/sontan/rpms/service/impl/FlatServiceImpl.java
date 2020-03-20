package com.sontan.rpms.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sontan.rpms.dao.FlatMapper;
import com.sontan.rpms.dao.UserMapper;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.service.FlatService;
import com.sontan.rpms.service.UserService;
import com.sontan.rpms.vo.FlatVo;
import javafx.scene.control.Pagination;
import org.springframework.stereotype.Service;


@Service
public class FlatServiceImpl extends ServiceImpl<FlatMapper, Flat> implements FlatService {

    @Override
    public Page<FlatVo> getFlatVo(Page<FlatVo> page) {
        return page.setRecords(this.baseMapper.getFlatVo(page));
    }


}
