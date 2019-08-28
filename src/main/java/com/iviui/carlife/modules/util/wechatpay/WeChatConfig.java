package com.iviui.carlife.modules.util.wechatpay;

/**
 * @author: ChengPan
 * @date: 2019/8/28
 * @description: 微信支付配置参数
 */
public class WeChatConfig {
    /**
     * 微信服务号APPID
     */
    public static String APPID="wx34d8fd96c0abf57a";
    /**
     * 微信支付的商户号
     */
    public static String MCHID="1526448301";
    /**
     * 微信支付的API密钥
     */
    public static String APIKEY="693d2b1Cf87111203C386f508ACe32ad";
    /**
     * 微信支付成功之后的回调地址【注意：当前回调地址必须是公网能够访问的地址】
     */
    public static String WECHAT_NOTIFY_URL_PC="http://47.104.107.142/nl_service/systemService/weixinPayCallBack";
    /**
     * 微信统一下单API地址
     */
    public static String UFDODER_URL="https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * true为使用真实金额支付，false为使用测试金额支付（1分）
     */
    public static String WXPAY="false";
}