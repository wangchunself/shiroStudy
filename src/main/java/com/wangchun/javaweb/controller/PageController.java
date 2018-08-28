package com.wangchun.javaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/8/28.
 */
@Controller
public class PageController {
    @RequestMapping("index.html")
    public String index(){
        return "index";
    }
    @RequestMapping("error.html")
    public String error(){
        return "error";
    }
}
