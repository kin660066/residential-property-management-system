package com.sontan.rpms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sontan.rpms.entity.PaymentDetail;
import com.sontan.rpms.entity.PaymentItem;
import com.sontan.rpms.service.PaymentDetailService;
import com.sontan.rpms.service.PaymentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    PaymentDetailService paymentDetailService;

    @Autowired
    PaymentItemService paymentItemService;

    @ResponseBody
    @RequestMapping("/statistics")
    public Map<String, List> statistics(String year) {
        Map<String, List> map=new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        if(StringUtils.isEmpty(year)){
            year=sdf.format(date);}
        List<String> statistics = paymentDetailService.getStatistics(year);
        List<String> months = paymentDetailService.getMonths();
        map.put("list",statistics);
        map.put("months",months);
        return map;
    }
    @ResponseBody
    @RequestMapping("/statisticsKinds")
    public Map<String, List> statisticsKinds(String year) {
        Map<String, List> map=new HashMap<>();
        List<List<String>> all = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        if(StringUtils.isEmpty(year)){
            year=sdf.format(date);}
        QueryWrapper<PaymentItem> wrapper = new QueryWrapper<>();
        wrapper.select("id");
        List<PaymentItem> list = paymentItemService.list(wrapper);
        for (PaymentItem item:list){
            Integer pid = item.getId();
            List<String> statistics = paymentDetailService.statisticsKinds(year,pid);
            all.add(statistics);
        }
        List<String> months = paymentDetailService.getMonths();
        List<String> paymentItem = paymentItemService.getPaymentItem();
        map.put("item",paymentItem);
        map.put("list",all);
        map.put("months",months);
        return map;
    }
}
