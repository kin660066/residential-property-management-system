package com.sontan.rpms.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.entity.PaymentDetail;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.service.PaymentDetailService;
import com.sontan.rpms.vo.FlatVo;
import com.sontan.rpms.vo.PaymentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (PaymentDetail)表控制层
 *
 * @author makejava
 * @since 2020-03-27 10:04:45
 */
@Controller
@RequestMapping("paymentDetail")
public class PaymentDetailController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private PaymentDetailService paymentDetailService;

    @ResponseBody
    @RequestMapping("paymentDetailList")
    public DataGridView paymentDetailList(@RequestParam("page")int pageIndex,
                                     @RequestParam("limit")int pageSize,String username,Integer month){
        Page<PaymentVo> page = new Page<>(pageIndex,pageSize);
        if (username!=null&&month!=null){
            paymentDetailService.getFlatVo2(page,username,month);
        }
        if (username!=null&&month==null){
            paymentDetailService.getFlatVo3(page,username);
        }
        if (username==null&&month!=null){
            paymentDetailService.getFlatVo4(page,month);
        }if (username==null&&month==null){
        paymentDetailService.getFlatVo(page);}
        List<PaymentVo> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }
    @ResponseBody
    @RequestMapping("/addPaymentDetail")
    public ResultObj addPaymentDetail(PaymentDetail paymentDetail, HttpSession session){
        String username = (String)session.getAttribute("userna");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        paymentDetail.setCreateby(username);
        paymentDetail.setCreatetime(new Date());
        paymentDetail.setState(0);
        paymentDetail.setYear(sdf.format(date));
        if(paymentDetailService.save(paymentDetail)){
            return ResultObj.LOGIN_ADD_SUCESS;}
        return ResultObj.LOGIN_ADD_ERROR;
    }
    @ResponseBody
    @RequestMapping("paymentDetailListOwner")
    public DataGridView paymentDetailListOwner(@RequestParam("page")int pageIndex,
                                     @RequestParam("limit")int pageSize,HttpSession session){
        Page<PaymentVo> page = new Page<>(pageIndex,pageSize);
        User user =(User)session.getAttribute("user");
        Integer id = user.getId();
        paymentDetailService.getFlatVo1(page,id);
        List<PaymentVo> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }
    @ResponseBody
    @RequestMapping("paymentDetailPay")
    public ResultObj paymentDetailPay(Integer id){
       UpdateWrapper<PaymentDetail> wrapper = new UpdateWrapper<>();
       wrapper.set("state",1).eq("id",id).set("paytime",new Date());
       if(paymentDetailService.update(wrapper))
           return ResultObj.PAY_SUCCESS;
        return ResultObj.PAY_ERROR;
    }
}