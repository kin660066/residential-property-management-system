package com.sontan.rpms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sontan.rpms.common.DataGridView;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.entity.UserFamily;
import com.sontan.rpms.service.UserFamilyService;
import com.sontan.rpms.service.UserService;
import com.sontan.rpms.utils.CardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
                                  @RequestParam("limit")int pageSize){
        IPage<User> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<User> wrapper=new QueryWrapper<>();
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
    public ResultObj addOwner(User user) {
      user.setAge(CardUtil.getAge(user.getCard()));
      user.setCreatetime(new Date());
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
     * 挑战业主家属页面
     * @param id
     * @param
     * @return
     */
    @RequestMapping("/toOwnerRe")
    public String toOwnerRe(Integer id, HttpSession session){
        session.setAttribute("rid",id);
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
                                  @RequestParam("limit")int pageSize,HttpSession session){
        int id =(int)session.getAttribute("rid");
        IPage<UserFamily> page = new Page<>(pageIndex,pageSize);
        QueryWrapper<UserFamily> wrapper=new QueryWrapper<>();
        wrapper.eq("userRelateId",id);
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
    public ResultObj addOwnerRe(UserFamily userFamily,HttpSession s) {
        userFamily.setUserrelateid((int)s.getAttribute("rid"));
        QueryWrapper<UserFamily> wrapper = new QueryWrapper<>();
        wrapper.eq("name", userFamily.getName()).or().eq("relationship", userFamily.getRelationship());
        wrapper.eq("userrelateid",(int)s.getAttribute("rid"));
        if (userFamilyService.getOne(wrapper) != null) {
            return ResultObj.RELATIONSHIP_ADD_REPEAT;
        } else {
            userFamilyService.save(userFamily);
        }
        return ResultObj.LOGIN_ADD_SUCESS;
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
}

