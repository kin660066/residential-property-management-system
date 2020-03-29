package com.sontan.rpms.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.PaymentItem;
import com.sontan.rpms.entity.PublicFacilities;
import com.sontan.rpms.service.PaymentItemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (PaymentItem)表控制层
 *
 * @author makejava
 * @since 2020-03-21 11:28:27
 */
@RestController
@RequestMapping("paymentItem")
public class PaymentItemController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private PaymentItemService paymentItemService;

    /**
     * 查询缴费项目项
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/paymentItemInfo")
    public DataGridView paymentItemInfo(@RequestParam("page")int pageIndex,
                                     @RequestParam("limit")int pageSize){
        IPage<PaymentItem> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<PaymentItem> wrapper=new QueryWrapper<>();
        paymentItemService.page(page,wrapper);
        List<PaymentItem> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }



    /**
     * 新增缴费项目项
     * @param paymentItem
     * @return
     */
    @ResponseBody
    @RequestMapping("/addPaymentItem")
    public ResultObj addPaymentItem(PaymentItem paymentItem) {
        if(paymentItemService.save(paymentItem)){
            return ResultObj.LOGIN_ADD_SUCESS;
        }
        return ResultObj.LOGIN_ADD_ERROR;
    }

    /**
     * 修改缴费项目项
     * @param paymentItem
     * @return
     */
    @ResponseBody
    @RequestMapping("/modPaymentItem")
    public ResultObj modOwner(PaymentItem paymentItem) {
        UpdateWrapper<PaymentItem> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",paymentItem.getId());
        if(paymentItemService.update(paymentItem,wrapper)==false){
            return ResultObj.LOGIN_MOD_ERROR;
        }
        return ResultObj.LOGIN_MOD_SUCESS;
    }

    /**
     * 删除缴费项目项
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delPaymentItem")
    public ResultObj delPaymentItem(Integer id) {
        UpdateWrapper<PaymentItem> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        if(paymentItemService.remove(wrapper))
            return ResultObj.DELETE_SUCCESS;
        else
            return ResultObj.DELETE_ERROR;
    }
    @ResponseBody
    @RequestMapping("/searchPaymentItem")
    public DataGridView searchPaymentItem(){
        QueryWrapper<PaymentItem> wrapper = new QueryWrapper<PaymentItem>();
        List<PaymentItem> list =paymentItemService.list(wrapper);
        return  new DataGridView(list);
    }
}