package com.iviui.carlife.modules.login.controller;

import com.iviui.carlife.modules.login.service.LoginService;
import com.iviui.carlife.modules.login.vo.SysPermission;
import com.iviui.carlife.modules.login.vo.SysRole;
import com.iviui.carlife.modules.login.vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: ChengPan
 * @date: 2019/5/7
 * @description: 登录测试页面跳转
 */
@Controller
class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value={"/","/index"})
    public String index(Map<String,Object> map){
        System.out.println("登陆成功");
        //获取到用户信息;
        Subject subject  = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getPrincipal();
        List<SysRole> roleList = userInfo.getRoleList();
        //用户拥有的所有权限的集合，有可能有重复权限
        List<SysPermission> permissions = new ArrayList<SysPermission>();
        for(SysRole role: roleList){
            permissions.addAll(role.getPermissions());
        }
        //用户拥有的所有权限 不重复
        Map<Long,SysPermission> permissionMap = new HashMap<Long,SysPermission>();
        for (SysPermission permission:permissions){
            permissionMap.put(permission.getId(),permission);
        }
        //传给页面展示 放所有的父节点对象，将子节点嵌套到父节点集合中
        List<SysPermission> resultData = new ArrayList<SysPermission>();
        for (Map.Entry<Long,SysPermission> entry : permissionMap.entrySet()) {
            SysPermission permission = entry.getValue();
            if (0 == permission.getParentId()){
                //一级菜单
                resultData.add(permission);
            } else {
                //二级菜单
                //根据pid找到父节点
                Long parentId = permission.getParentId();
                //父节点
                SysPermission parentPermission = permissionMap.get(parentId);
                if (parentPermission.getChildren() == null){
                    parentPermission.setChildren(new ArrayList());
                }
                parentPermission.getChildren().add(permission);

            }

        }
        map.put("userInfo",userInfo);
        map.put("list",resultData);
        return"/index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return"/login";
    }

    @RequestMapping(value = "/register", method=RequestMethod.GET)
    public String register() {
        return "registerPage";
    }

    @RequestMapping(value = "/register", method=RequestMethod.POST)
    public String doRegister(UserInfo userInfo) {
        // 生成uuid
        String id = UUID.randomUUID().toString().replaceAll("-","");
        userInfo.setSalt(id);
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(userInfo.getCredentialsSalt());
        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPs = new SimpleHash("MD5", userInfo.getPassword(), salt, 2).toHex();
        System.out.println(newPs);
        userInfo.setPassword(newPs);
        userInfo.setName("测试用户");
        userInfo.setState((byte) 0);
        List<SysRole> roleList = new ArrayList<SysRole>();
        SysRole sysRole = new SysRole();
        sysRole.setId((long) 2);
        roleList.add(sysRole);
        userInfo.setRoleList(roleList);
        boolean result = loginService.registerData(userInfo);

        return "/register";
    }

    @RequestMapping(value="/noAuth",method= RequestMethod.GET)
    public String noAuth(){
        return "noAuth";
    }
    // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
        System.out.println("HomeController.login()");
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute("shiroLoginFailure");

        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 此方法不处理登录成功,由shiro进行处理.
        return "/login";
    }
}