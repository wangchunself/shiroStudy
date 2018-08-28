package com.wangchun.javaweb.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/8/28.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @RequestMapping("list.html")
    public String list(){
        return "menu_list";
    }
    @RequestMapping("go_edit.html")
    @RequiresPermissions("menu:edit")
    public String goEdit(){
        return "menu_list";
    }
}
