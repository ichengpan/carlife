package com.iviui.carlife.modules.role.controller;

import com.iviui.carlife.modules.role.service.RoleService;
import com.iviui.carlife.modules.role.vo.RoleInfo;
import com.iviui.carlife.modules.user.vo.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/5/16
 * @description: 角色信息
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value="/roleInfo",method= RequestMethod.GET)
    @RequiresPermissions("role:info")
    public String roleInfo(){
        return"role/roleInfo";
    }

    @RequestMapping(value = "/roleList", method = RequestMethod.GET)
    @RequiresPermissions("role:query")
    @ResponseBody
    public Map userList(Integer page, Integer limit, RoleInfo roleInfo) {
        roleInfo.setPagenum(page);
        roleInfo.setPagesize(limit);
        Map<String, Object> result = new HashMap<>();
        Integer count = roleService.countRoleInfo(roleInfo);
        List<Map<String, Object>> list = roleService.listRoleInfo(roleInfo);
        result.put("code", "0");
        result.put("msg", "");
        result.put("count", count);
        result.put("data", list);
        return result;
    }

}