package com.sontan.rpms.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sontan.rpms.dao.FlatMapper;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.service.FlatService;
import com.sontan.rpms.vo.FlatVo;
import org.springframework.stereotype.Service;

@Service
public class FlatServiceImpl extends ServiceImpl<FlatMapper, Flat> implements FlatService {

    @Override
    public Page<FlatVo> getFlatVo(Page<FlatVo> page) {
        return page.setRecords(this.baseMapper.getFlatVo(page));
    }
    @Override
    public Page<FlatVo> getFlatVo1(Page<FlatVo> page,Integer ownerid) {
        return page.setRecords(this.baseMapper.getFlatVo1(page,ownerid));
    }

}
