package com.sontan.rpms.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.PublicFacilities;
import com.sontan.rpms.service.PublicFacilitiesService;
import com.sontan.rpms.utils.CardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (PublicFacilities)表控制层
 *
 * @author makejava
 * @since 2020-03-20 08:45:11
 */
@Controller
@RequestMapping("/fac")
public class PublicFacilitiesController  {
    /**
     * 服务对象
     */
    @Autowired
    private PublicFacilitiesService publicFacilitiesService;

    @ResponseBody
    @RequestMapping("/facilityInfo")
    public DataGridView facilityInfo(@RequestParam("page")int pageIndex,
                                  @RequestParam("limit")int pageSize){
        IPage<PublicFacilities> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<PublicFacilities> wrapper=new QueryWrapper<>();
        publicFacilitiesService.page(page,wrapper);
        List<PublicFacilities> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }

    @RequestMapping("/toAddFacility")
    public String toAddFacility(){
        return "page/facility/addFacility.html";
    }




    @ResponseBody
    @RequestMapping("/addFacility")
    public ResultObj addFacility(PublicFacilities facilities) {
        if(publicFacilitiesService.save(facilities)){
            return ResultObj.LOGIN_ADD_SUCESS;
        }
        return ResultObj.LOGIN_ADD_ERROR;
    }

    @ResponseBody
    @RequestMapping("/modFacility")
    public ResultObj modOwner(PublicFacilities facilities) {
        UpdateWrapper<PublicFacilities> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",facilities.getId());
        if(publicFacilitiesService.update(facilities,wrapper)==false){
            return ResultObj.LOGIN_MOD_ERROR;
        }
        return ResultObj.LOGIN_MOD_SUCESS;
    }

    /**
     * 删除业主
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delFacility")
    public ResultObj delOwner(Integer id) {
        UpdateWrapper<PublicFacilities> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        if(publicFacilitiesService.remove(wrapper))
            return ResultObj.DELETE_SUCCESS;
        else
            return ResultObj.DELETE_ERROR;
    }

}