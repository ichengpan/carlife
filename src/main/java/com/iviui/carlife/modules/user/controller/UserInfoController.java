package com.iviui.carlife.modules.user.controller;

import com.iviui.carlife.modules.user.service.UserInfoService;
import com.iviui.carlife.modules.user.vo.User;
import com.iviui.carlife.modules.util.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @RequiresPermissions("userInfo:info")
    public String userInfo() {
        return "/user/userInfo";
    }

    @RequestMapping(value = "/userAdd", method = RequestMethod.GET)
    @RequiresPermissions("userInfo:add")
    public String userAdd() {
        return "/user/userAdd";
    }

    @RequestMapping(value = "/userDel", method = RequestMethod.GET)
    @RequiresPermissions("userInfo:del")
    public String userDel() {
        return "/user/userDel";
    }

    @RequestMapping(value = "/userUpdate", method = RequestMethod.GET)
    @RequiresPermissions("userInfo:edit")
    public String userUpdate() {
        return "/user/userUpdate";
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @RequiresPermissions("userInfo:query")
    @ResponseBody
    public Map userList(Integer page, Integer limit, User userInfo) {
        userInfo.setPagenum(page);
        userInfo.setPagesize(limit);
        Map<String, Object> result = new HashMap<>();
        Integer count = userInfoService.countUserInfo(userInfo);
        List<Map<String, Object>> list = userInfoService.listUserInfo(userInfo);
        result.put("code", "0");
        result.put("msg", "");
        result.put("count", count);
        result.put("data", list);
        return result;
    }

    @RequestMapping(value = "/userInfoDownLoad")
    public String userInfoDownLoad(HttpServletRequest request, HttpServletResponse response, User userInfo) {
        List<Map<String, Object>> resultList = userInfoService.userInfoDownLoad(userInfo);
        String[] keys = {"uid", "name", "username", "state"};
        String[] columnNames = {"用户ID", "昵称", "用户名", "状态"};
        Workbook wb = ExcelUtil.createWorkBook(resultList, keys, columnNames);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            wb.write(os);
            ExcelUtil.getResponsePage(request, response, os, "用户信息表_" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}