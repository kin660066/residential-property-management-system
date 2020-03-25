package com.sontan.rpms.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.*;
import com.sontan.rpms.service.MessageService;
import com.sontan.rpms.service.ParkingService;
import com.sontan.rpms.service.PublicFacilitiesService;
import com.sontan.rpms.utils.RoomNoUtil;
import com.sontan.rpms.vo.FlatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Message)表控制层
 *
 * @author makejava
 * @since 2020-03-23 09:47:33
 */
@Controller
@RequestMapping("/message")
public class MessageController extends ApiController {
    /**
     * 服务对象
     */
    @Autowired
    private MessageService messageService;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private PublicFacilitiesService publicFacilitiesService;
    @ResponseBody
    @RequestMapping("/messageInfo")
    public DataGridView messageInfo(@RequestParam("page")int pageIndex,
                                    @RequestParam("limit")int pageSize,HttpSession session){
        Page<Message> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<Message> wrapper = new QueryWrapper<>();
        User user =(User)session.getAttribute("user");
        wrapper.eq("username",user.getUsername());
        messageService.page(page,wrapper);
        List<Message> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }

    @ResponseBody
    @RequestMapping("/addMessage")
    public ResultObj addMessage(Message message,HttpSession session) {
        User user =(User)session.getAttribute("user");
        message.setUsername(user.getUsername());
        message.setTitle("业主家属绑定申请");
        message.setFlag(0);
        message.setType(1);
        message.setCreatetime(new Date());

            if(messageService.save(message)){
                return ResultObj.LOGIN_ADD_SUCESS;
        }
        return ResultObj.LOGIN_ADD_SUCESS;
    }
    @RequestMapping("toAddMessage1")
    public String toAddMessage1(){
        return "page/message/message1.html";
    }
    @RequestMapping("toAddMessage2")
    public String toAddMessage2(){
        return "page/message/message2.html";
    }
    @RequestMapping("toAddMessage3")
    public String toAddMessage3(){
        return "page/message/message3.html";
    }
    @RequestMapping("toAddMessage4")
    public String toAddMessage4(){
        return "page/message/message4.html";
    }
    @ResponseBody
    @RequestMapping("/searchCarNo")
    public DataGridView searchCarNo(){
        QueryWrapper<Parking> wrapper = new QueryWrapper<Parking>();
        wrapper.select("no");
        List<Parking> list =parkingService.list(wrapper);
        return  new DataGridView(list);
    }
    @ResponseBody
    @RequestMapping("/addMessage2")
    public ResultObj addMessage2(Message message,HttpSession session) {
        User user =(User)session.getAttribute("user");
        message.setUsername(user.getUsername());
        message.setTitle("车位绑定申请");
        message.setFlag(0);
        message.setType(2);
        message.setCreatetime(new Date());
        if(messageService.save(message)){
            return ResultObj.LOGIN_ADD_SUCESS;
        }
        return ResultObj.LOGIN_ADD_SUCESS;
    }
    @ResponseBody
    @RequestMapping("/searchFac")
    public DataGridView searchFac(){
        QueryWrapper<PublicFacilities> wrapper = new QueryWrapper<PublicFacilities>();
        List<PublicFacilities> list =publicFacilitiesService.list(wrapper);
        return  new DataGridView(list);
    }
    @ResponseBody
    @RequestMapping("/addMessage3")
    public ResultObj addMessage3(Message message,HttpSession session) {
        User user =(User)session.getAttribute("user");
        message.setUsername(user.getUsername());
        message.setTitle("公共设备维修申报");
        message.setFlag(0);
        message.setType(3);
        message.setCreatetime(new Date());
        if(messageService.save(message)){
            return ResultObj.LOGIN_ADD_SUCESS;
        }
        return ResultObj.LOGIN_ADD_SUCESS;
    }
    @ResponseBody
    @RequestMapping("/addMessage4")
    public ResultObj addMessage4(Message message,HttpSession session) {
        User user =(User)session.getAttribute("user");
        message.setUsername(user.getUsername());
        message.setTitle("投诉");
        message.setFlag(0);
        message.setType(0);
        message.setCreatetime(new Date());
        if(messageService.save(message)){
            return ResultObj.LOGIN_ADD_SUCESS;
        }
        return ResultObj.LOGIN_ADD_SUCESS;
    }
    @RequestMapping("/messageDetail")
    public String messageDetail(Integer id, ModelMap model){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Message message=messageService.getById(id);
        model.addAttribute("title",message.getTitle());
        model.addAttribute("id",message.getId());
        model.addAttribute("content",message.getContent());
        model.addAttribute("type",message.getType());
        model.addAttribute("creattetime",simpleDateFormat.format(message.getCreatetime()));
        model.addAttribute("flag",message.getFlag());
        return  "/page/message/messageDetail.html";
    }
    @ResponseBody
    @RequestMapping("/confirm")
    public ResultObj confirm(Integer id){
        UpdateWrapper<Message> wrapper1=new UpdateWrapper<>();
        wrapper1.eq("id",id);
         wrapper1.set("flag",1);
        if(messageService.update(wrapper1)){
         return ResultObj.CONFIRM_SUCCESS;}
        return ResultObj.CONFIRM_ERROR;
    }
    @ResponseBody
    @RequestMapping("/drawback")
    public ResultObj drawback(Message message){
        UpdateWrapper<Message> wrapper1=new UpdateWrapper<>();
        wrapper1.eq("id",message.getId());
        wrapper1.set("flag",2);
        wrapper1.set("remark",message.getRemark());
        if(messageService.update(wrapper1)){
            return ResultObj.CONFIRM_SUCCESS;}
        return ResultObj.CONFIRM_ERROR;
    }
}