package com.sontan.rpms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.dao.FlatMapper;
import com.sontan.rpms.entity.Flat;
import com.sontan.rpms.entity.Parking;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.service.FlatLayoutService;
import com.sontan.rpms.service.FlatService;
import com.sontan.rpms.service.UserService;
import com.sontan.rpms.utils.RoomNoUtil;
import com.sontan.rpms.vo.FlatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/flat")
public class FlatController {
    @Autowired
    FlatService flatService;
    @Autowired
    FlatLayoutService flatLayoutService;
    @Autowired
    UserService userService;
    /**
     * 加载房屋信息
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/flatInfoList")
    public DataGridView flatInfoList(@RequestParam("page")int pageIndex,
                           @RequestParam("limit")int pageSize){
        Page<FlatVo> page = new Page<>(pageIndex,pageSize);
        flatService.getFlatVo(page);
        List<FlatVo> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }

    /**
     * 跳转添加房屋信息页面
     * @return
     */
    @RequestMapping("/toAddFlatInfo")
    public String toAddFlatInfo(){
        return "/page/flat/addFlat.html";
    }

    /**
     * 添加房屋信息
     * @param flat
     * @return
     */
    @ResponseBody
    @RequestMapping("/addFlat")
    public ResultObj addFlat(Flat flat) {
        flat.setNo(RoomNoUtil.getRoomNo(flat.getBlock(),flat.getUnit(),flat.getFloor(),flat.getRoom()));
        QueryWrapper<Flat> wrapper = new QueryWrapper<>();
        wrapper.eq("no", flat.getNo());
        if (flatService.getOne(wrapper) != null) {
            return ResultObj.FLAT_ADD_REPEAT;
        } else {
            flatService.save(flat);
        }
        return ResultObj.LOGIN_ADD_SUCESS;
    }

    /**
     * 删除房屋信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delFlat")
    public ResultObj delFlat(Integer id) {
        UpdateWrapper<Flat> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",id);
        if(flatService.remove(wrapper)==true)
            return ResultObj.DELETE_SUCCESS;
        else
            return ResultObj.DELETE_ERROR;
    }

    /**
     * 解除业主房屋信息
     * @param ownerid
     * @return
     */
    @ResponseBody
    @RequestMapping("/unBindFlat")
    public ResultObj unBindFlat(Integer ownerid){
        UpdateWrapper<Flat> wrapper = new UpdateWrapper<>();
        wrapper.set("ownerid",null).set("state",1).eq("ownerid",ownerid);
        if(flatService.update(wrapper)){
            return ResultObj.UNBIND_SUCCESS;
        }
        return ResultObj.UNBIND_ERROR;
    }

    /**
     * 舔砖绑定页面
     * @param no
     * @param request
     * @return
     */
    @RequestMapping("toBindFlat")
    public String toBindFlat(String no, HttpServletRequest request){
        System.out.println(no);
        request.setAttribute("no",no);
        return "/page/flat/bindFlat.html";
    }

    /**
     * 查询业主名字
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchOwner")
    public DataGridView searchOwner(){
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        List<User> list =userService.list(wrapper);
        return  new DataGridView(list);
    }

    /**
     * 绑定业主房屋
     * @param no
     * @param owner
     * @return
     */
    @ResponseBody
    @RequestMapping("/addBindFlat")
    public ResultObj addBindFlat(String no,String owner){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",owner);
        User user=userService.getOne(wrapper);
        UpdateWrapper<Flat> wrapper1 = new UpdateWrapper();
        wrapper1.set("ownerid",user.getId()).set("state",0).set("createtime",new Date()).eq("no",no);
        if(flatService.update(wrapper1)){
        return ResultObj.BIND_SUCCESS;}
        return ResultObj.BIND_ERROR;
    }

    /**
     * 业主查看房屋信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/flatOwnerInfo")
    public DataGridView flatOwnerInfo(){
        Page<FlatVo> page = new Page<>();
        flatService.getFlatVo1(page,1);
        List<FlatVo> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }
}
