package com.sontan.rpms.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.vo.FlatVo;
import org.springframework.stereotype.Service;


@Service
public interface FlatService extends IService<Flat> {

    Page<FlatVo> getFlatVo(Page<FlatVo> page);

    Page<FlatVo> getFlatVo1(Page<FlatVo> page,Integer ownerid);

}
