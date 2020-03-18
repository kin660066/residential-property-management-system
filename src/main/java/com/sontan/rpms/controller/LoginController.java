package com.sontan.rpms.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sontan.rpms.common.ResultObj;
import com.sontan.rpms.entity.User;
import com.sontan.rpms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
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
    public ResultObj login(String account,String password,String code,HttpSession session){
        System.out.println(code);
        Object codeSession = session.getAttribute("code");
        if(code!=null&&code.equals(codeSession)) {
            QueryWrapper<User> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("account", account);
            queryWrapper.eq("password", password);
            User user = userService.getOne(queryWrapper);
            if(null!=user) {
                session.setAttribute("user", user);
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
}
