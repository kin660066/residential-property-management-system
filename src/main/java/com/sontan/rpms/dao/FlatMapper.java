package com.sontan.rpms.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.vo.FlatVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FlatMapper extends BaseMapper<Flat> {
    @Select("SELECT flat_info.*,flat_layout.layout from flat_info ,flat_layout  where flat_info.layoutid=flat_layout.id")
    List<FlatVo> getFlatVo(IPage<FlatVo> page);
    @Select("SELECT flat_info.*,flat_layout.layout from flat_info ,flat_layout  where flat_info.layoutid=flat_layout.id and flat_info.ownerid= #{ownerid}")
    List<FlatVo> getFlatVo1(IPage<FlatVo> page,Integer ownerid);
}
