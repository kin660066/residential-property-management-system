package com.sontan.rpms.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * description：登录控制层
 * @version 1.0
 * @param
 * @return
 * @author Vergil
 * @since $date$ $time$
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/tologin")
    public String tologin(){
        return "page/login/login";
    }


    @RequestMapping("/login")
    @ResponseBody
    public ResultObj login(String account, String password, String code, HttpSession session, HttpServletRequest request){
        Object codeSession = session.getAttribute("code");
        if(code!=null&&code.equals(codeSession)) {
            QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("account", account);
            queryWrapper.eq("password", password);
            User user = userService.getOne(queryWrapper);
            if(null!=user) {
                session.setAttribute("user", user);
                session.setAttribute("userna", user.getUsername());
               request.getSession().setAttribute("aaa",user);
               request.setAttribute("111","111");
                return ResultObj.LOGIN_SUCCESS;
            }else {
                return ResultObj.LOGIN_ERROR_ACCOUNTORPWD;
            }
        }else {
            return ResultObj.LOGIN_ERROR_CODE;
        }
    }

    @RequestMapping("/getCode")
    public void getCode(HttpSession session, HttpServletResponse res) throws Exception{
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(116, 36, 4, 0);
        String code = captcha.getCode();
        session.setAttribute("code",code);
        System.out.println(code);
        ServletOutputStream outputStream = res.getOutputStream();
        captcha.write(outputStream);
        outputStream.close();
    }
    @RequestMapping("chara")
    @ResponseBody
    public Integer chara(HttpSession session){
        User user=(User)session.getAttribute("user");
        return user.getType();
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "page/login/login.html";
    }
    @RequestMapping("user")
    public String user(HttpSession session, ModelMap model){
        User user = (User)session.getAttribute("user");
        model.addAttribute("id",user.getId());
        model.addAttribute("account",user.getAccount());
        model.addAttribute("username",user.getUsername());
        model.addAttribute("sex",user.getSex());
        model.addAttribute("phone",user.getPhone());
        model.addAttribute("address",user.getAddress());
        model.addAttribute("age",user.getAge());
        model.addAttribute("type",user.getType());
        model.addAttribute("card",user.getCard());
        return "page/user/userInfo.html";
    }
    @RequestMapping("tomodAvatar")
    public String tomodAvatar( HttpSession session, ModelMap model){
        User user = (User)session.getAttribute("user");
        model.addAttribute("id",user.getId());
        return "/page/user/modAvatar.html";
    }
    @RequestMapping("toModPwd")
    public String toModPwd( HttpSession session, ModelMap model){
        User user = (User)session.getAttribute("user");
        model.addAttribute("id",user.getId());
        model.addAttribute("pwd",user.getPassword());
        return "/page/user/changePwd.html";
    }

    @ResponseBody
    @RequestMapping("/modPwd")
    public ResultObj modPwd(String pwd1,Integer id){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("password",pwd1).eq("id",id);
        if(userService.update(wrapper)){
            return ResultObj.LOGIN_MOD_SUCESS;
        }
        return ResultObj.LOGIN_MOD_ERROR;
    }
    @ResponseBody
    @RequestMapping("/modAvatar")
    public ResultObj modAvatar(Integer id,String images){
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("url",images).eq("id",id);
        if(userService.update(wrapper)){
            return ResultObj.LOGIN_MOD_SUCESS;
        }
        return ResultObj.LOGIN_MOD_ERROR;
    }
    @RequestMapping("ok")
    @ResponseBody
    public User ok(HttpSession session){
        User user=(User)session.getAttribute("user");
        return user;
    }
    @RequestMapping("url")
    @ResponseBody
    public String url(HttpSession session){
        User user=(User)session.getAttribute("user");
        return user.getUrl();
    }
}
