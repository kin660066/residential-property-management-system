package com.sontan.rpms.service;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.vo.FlatVo;
import javafx.scene.control.Pagination;
import org.springframework.stereotype.Service;


@Service
public interface FlatService extends IService<Flat> {

    Page<FlatVo> getFlatVo(Page<FlatVo> page);
}
