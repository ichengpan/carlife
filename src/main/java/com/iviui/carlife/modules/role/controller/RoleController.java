package com.iviui.carlife.modules.role.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: ChengPan
 * @date: 2019/5/16
 * @description: 角色信息
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @RequestMapping(value="/roleInfo",method= RequestMethod.GET)
    @RequiresPermissions("role:info")
    public String roleInfo(){
        return"/role/roleInfo";
    }
}