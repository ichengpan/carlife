package com.iviui.carlife.modules.user.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: ChengPan
 * @date: 2019/5/13
 * @description: 菜单信息
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @RequestMapping(value="/userInfo",method= RequestMethod.GET)
    @RequiresPermissions("userInfo:info")
    public String userInfo(){
        return"/user/userInfo";
    }

    @RequestMapping(value="/userAdd",method= RequestMethod.GET)
    @RequiresPermissions("userInfo:add")
    public String userAdd(){
        return"/user/userAdd";
    }

    @RequestMapping(value="/userDel",method= RequestMethod.GET)
    @RequiresPermissions("userInfo:del")
    public String userDel(){
        return"/user/userDel";
    }

    @RequestMapping(value="/userUpdate",method= RequestMethod.GET)
    @RequiresPermissions("userInfo:edit")
    public String userUpdate(){
        return "/user/userUpdate";
    }

    @RequestMapping(value="/userList",method= RequestMethod.GET)
    @RequiresPermissions("userInfo:query")
    public String userList(){
        return "/user/userList";
    }
}