package com.sontan.rpms.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.Parking;
import com.sontan.rpms.entity.PaymentItem;
import com.sontan.rpms.service.ParkingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * (Parking)表控制层
 *
 * @author makejava
 * @since 2020-03-21 15:06:52
 */
@Controller
@RequestMapping("parking")
public class ParkingController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ParkingService parkingService;

    /**
     * 查询停车位信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/parkingInfo")
    public DataGridView parkingInfo(@RequestParam("page")int pageIndex,
                                        @RequestParam("limit")int pageSize){
        IPage<Parking> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<Parking> wrapper=new QueryWrapper<>();
        parkingService.page(page,wrapper);
        List<Parking> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }
    /**
     * 新增车位信息
     * @param parking
     * @return
     */
    @ResponseBody
    @RequestMapping("/addParking")
    public ResultObj addParking(Parking parking) {
        if(parkingService.save(parking)){
            return ResultObj.LOGIN_ADD_SUCESS;
        }
        return ResultObj.LOGIN_ADD_ERROR;
    }

    /**
     * 修改车位信息
     * @param parking
     * @return
     */
    @ResponseBody
    @RequestMapping("/modParking")
    public ResultObj modParking(Parking parking) {
        UpdateWrapper<Parking> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",parking.getId());
        if(parkingService.update(parking,wrapper)==false){
            return ResultObj.LOGIN_MOD_ERROR;
        }
        return ResultObj.LOGIN_MOD_SUCESS;
    }

    /**
     * 根据ID删除车位信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delParking")
    public ResultObj delParking(Integer id) {
        UpdateWrapper<Parking> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        if(parkingService.remove(wrapper))
            return ResultObj.DELETE_SUCCESS;
        else
            return ResultObj.DELETE_ERROR;
    }

    /**
     * 根据业主id查询车位
     * @return
     */
    @ResponseBody
    @RequestMapping("/parkingOwnerInfo")
    public DataGridView parkingOwnerInfo(){
        IPage<Parking> page = new Page<>();
        QueryWrapper<Parking> wrapper=new QueryWrapper<>();
        wrapper.eq("ownerid",1);
        parkingService.page(page,wrapper);
        List<Parking> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }
    @ResponseBody
    @RequestMapping("/unBindParking")
    public ResultObj unBindParking(Integer id) {
        UpdateWrapper<Parking> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id).set("ownerid",null).set("starttime",null).set("endtime",null).set("carno",null).set("state",1);
        if(parkingService.update(wrapper))
            return ResultObj.UNBIND_SUCCESS;
        else
            return ResultObj.UNBIND_ERROR;
    }
    @RequestMapping("/toParkingBind")
    public String toParkingBind(String no, HttpServletRequest request){
        request.setAttribute("no",no);
        return "/page/parking/bindParking.html";
    }
    @ResponseBody
    @RequestMapping("/parkingBind")
    public ResultObj parkingBind(Integer ownerid,String starttime,String endtime, String carno,String no) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {

            UpdateWrapper<Parking> wrapper = new UpdateWrapper<>();
            wrapper.eq("no",no).set("ownerid",ownerid).set("starttime",sdf.parse(starttime))
                    .set("endtime",sdf.parse(endtime)).set("carno",carno).set("state",0);
            if(parkingService.update(wrapper))
                return ResultObj.BIND_SUCCESS;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ResultObj.BIND_ERROR;
    }
}