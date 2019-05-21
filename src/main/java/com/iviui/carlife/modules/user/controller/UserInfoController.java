package com.iviui.carlife.modules.user.controller;

import com.iviui.carlife.modules.user.service.UserInfoService;
import com.iviui.carlife.modules.user.vo.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/5/13
 * @description: 菜单信息
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

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
    @ResponseBody
    public Map userList(Integer page, Integer limit, User userInfo){
        userInfo.setPagenum(page);
        userInfo.setPagesize(limit);
        Map<String, Object> result = new HashMap<>();
        Integer count = userInfoService.countUserInfo(userInfo);
        List<Map<String,Object>> list = userInfoService.listUserInfo(userInfo);
        result.put("code","0");
        result.put("msg","");
        result.put("count",count);
        result.put("data",list);
        return result;
    }
}