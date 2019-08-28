package com.iviui.carlife.modules.pay.wechat.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: ChengPan
 * @date: 2019/8/28
 * @description: 微信支付
 */
@Controller
@RequestMapping("/pay/*")
public class WechatPayController {

    @RequestMapping("wechatPay")
    @RequiresPermissions("pay:wechatpay")
    public String wechatPay() {
        return "pay/wechat/wechatPay";
    }
}