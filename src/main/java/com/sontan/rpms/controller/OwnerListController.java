package com.sontan.rpms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.Parking;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.entity.UserFamily;
import com.sontan.rpms.service.UserFamilyService;
import com.sontan.rpms.service.UserService;
import com.sontan.rpms.utils.CardUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/Owner")
public class OwnerListController {
    @Autowired
    UserService userService;
    @Autowired
    UserFamilyService userFamilyService;

    /**
     * 加载业主页
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/OwnerList")
    public DataGridView ownerList(@RequestParam("page")int pageIndex,
                                  @RequestParam("limit")int pageSize,String no,String username){
        IPage<User> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        if (no!=null){
        if (!no.equals("")){
        wrapper.like("account",no);}}
        if (username!=null){
        if (!username.equals("")){
        wrapper.like("username",username);}}
        userService.page(page,wrapper);
        List<User> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }

    @RequestMapping("/toAddOwner")
    public String toAddOwner(){
     return "page/Owners/addOwner.html";
    }



    /**
     * 添加业主
     * @param user
     * @return
     */
  @ResponseBody
  @RequestMapping("/addOwner")
    public ResultObj addOwner(User user,HttpSession session) {
      User admin = (User)session.getAttribute("user");
      user.setCreateby(admin.getUsername());
      if (user.getCard()!=null){
      user.setAge(CardUtil.getAge(user.getCard()));}
      user.setCreatetime(new Date());
      user.setUrl("/resources/images/22.jpg");
      QueryWrapper<User> wrapper = new QueryWrapper<>();
      wrapper.eq("account", user.getAccount()).or().eq("username", user.getUsername());
      if (userService.getOne(wrapper) != null) {
          return ResultObj.LOGIN_ADD_REPEAT;
      } else {
          userService.save(user);
      }
      return ResultObj.LOGIN_ADD_SUCESS;
  }
    /**
     * 修改业主信息
     */
    @ResponseBody
    @RequestMapping("/modOwner")
    public ResultObj modOwner(User user) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("account",user.getAccount());
        if(userService.update(user,wrapper)==false){
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
    @RequestMapping("/delOwner")
    public ResultObj delOwner(Integer id) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("account",id);
       if(userService.remove(wrapper)==true)
        return ResultObj.DELETE_SUCCESS;
       else
           return ResultObj.DELETE_ERROR;
    }

    /**
     * 跳转业主家属页面
     * @param id
     * @param
     * @return
     */
    @RequestMapping("/toOwnerRe")
    public String toOwnerRe(Integer id, HttpSession session){
        session.setAttribute("relateid",id);
        System.out.println(id);
        return "page/Owners/OwnerRe.html";
    }

    /**
     * 加载家属
     * @param pageIndex
     * @param pageSize
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/ownerReList")
    public DataGridView ownerReList(@RequestParam("page")int pageIndex,
                                  @RequestParam("limit")int pageSize,HttpSession session ){
        IPage<UserFamily> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<UserFamily> wrapper=new QueryWrapper<>();
        wrapper.eq("userRelateId",session.getAttribute("relateid"));
        userFamilyService.page(page,wrapper);
        List<UserFamily> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }

    @RequestMapping("/toAddOwnerRe")
    public String toAddOwnerRe(){
        return "page/Owners/addOwnerRe.html";
    }

    /**
     * 添加家属成员
     * @param userFamily
     * @return
     */
    @ResponseBody
    @RequestMapping("/addOwnerRe")
    public ResultObj addOwnerRe(UserFamily userFamily,HttpSession session) {
        userFamily.setUserrelateid((int)session.getAttribute("relateid"));
        QueryWrapper<UserFamily> wrapper = new QueryWrapper<>();
        wrapper.eq("name", userFamily.getName()).or().eq("relationship", userFamily.getRelationship());
        wrapper.eq("id",session.getAttribute("relateid"));
        if (userFamilyService.getOne(wrapper) != null) {
            return ResultObj.RELATIONSHIP_ADD_REPEAT;
        } else {
            userFamilyService.save(userFamily);
        }
        return ResultObj.LOGIN_ADD_SUCESS;
    }

    /**
     * 修改家属信息
     * @param userFamily
     * @return
     */
    @ResponseBody
    @RequestMapping("/modOwnerRe")
    public ResultObj modOwnerRe(UserFamily userFamily) {
        UpdateWrapper<UserFamily> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",userFamily.getId());
        if(userFamilyService.update(userFamily,wrapper)==false){
            return ResultObj.LOGIN_MOD_ERROR;
        }
        return ResultObj.LOGIN_MOD_SUCESS;
    }
    /**
     * 根据业主id删除家属成员
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delOwnerRe")
    public ResultObj delOwnerRe(Integer id,HttpSession s) {
        int userrelatieid=(int)s.getAttribute("rid");
        UpdateWrapper<UserFamily> wrapper = new UpdateWrapper<>();
        wrapper.eq("userrelateid",userrelatieid).eq("id",id);
        if(userFamilyService.remove(wrapper)==true)
            return ResultObj.DELETE_SUCCESS;
        else
            return ResultObj.DELETE_ERROR;
    }

    /**
     * 更新业主
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/updataUser")
    public ResultObj updataUser(User user){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("password",user.getPassword()).eq("id",user.getId());
        if(userService.update(wrapper)){
        return ResultObj.LOGIN_MOD_SUCESS;}
        return ResultObj.LOGIN_MOD_ERROR;
    }
    @ResponseBody
    @RequestMapping("/adminList")
    public DataGridView adminList(@RequestParam("page")int pageIndex,
                                  @RequestParam("limit")int pageSize){
        IPage<User> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("type",1).or().eq("type",2);
        userService.page(page,wrapper);
        List<User> list =page.getRecords();
        return  new DataGridView(page.getTotal(),list);
    }
    @RequestMapping("/toAddAdmin")
    public String toAddAdmin(){
        return "page/admin/addAdmin.html";
    }


}

