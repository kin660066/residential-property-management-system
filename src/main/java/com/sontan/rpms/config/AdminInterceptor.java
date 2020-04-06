package com.sontan.rpms.config;


import com.sontan.rpms.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null || user.equals("") ){
            //未登陆，返回登陆页面
            System.out.println("false");
            session.setAttribute("msg","请登录");
            request.getRequestDispatcher("/").forward(request,response);
            return false;

        }
        System.out.println("true");
        return true;
    }
}