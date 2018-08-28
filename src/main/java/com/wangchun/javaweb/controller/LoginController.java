package com.wangchun.javaweb.controller;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018/8/28.
 */
@Controller
public class LoginController {
    @RequestMapping("goLogin.html")
    public String goLogin(){
        return "login";
    }
    @RequestMapping("logout.html")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }
    @RequestMapping("login.html")
    public String login(String username,String password,HttpServletRequest req){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try{
            subject.login(token);
            return "redirect:index.html";
        }catch (AuthenticationException e){
            e.printStackTrace();
            req.setAttribute("error","用户名或密码错误");
            return "login";
        }

    }
}
