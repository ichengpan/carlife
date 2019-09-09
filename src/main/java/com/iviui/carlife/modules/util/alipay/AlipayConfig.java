package com.iviui.carlife.modules.util.alipay;

/**
 * @author: ChengPan
 * @date: 2019/9/9
 * @description: 支付宝配置参数
 */
public class AlipayConfig {

    /**
     * APP支付宝支付业务：APP_ID
     */
    public static String APP_ID = "20180xxxxxxxxxxxxxx";

    /**
     * 商户的私钥,使用支付宝自带的openssl工具生成
     */
    public static String MERCHANT_PRIVATE_KEY = "xxxxxxxxxxxxxxxxxxx";

    /**
     * 支付宝的公钥，去open.alipay.com对应应用下查看
     */
    public static String ALIPAY_PUBLIC_KEY = "xxxxxxxxxxxxxxxxxxxxx";

    /**
     * 支付宝回调地址
     */
    public static String NOTIFY_URL = "xxxxxxxxxxxxxxxxxxxxx";

    /**
     * 签名方式
     */
    public static String SIGN_TYPE = "RSA2";

    /**
     * 字符编码格式
     */
    public static String CHARSET = "utf-8";

    /**
     * 返回格式
     */
    public static String FORMAT = "json";

    /**
     * 支付宝网关
     */
    public static String GETEWAY_URL="https://openapi.alipay.com/gateway.do";

    /**
     * 超时时间 可空
     */
    public static String TIMEOUT_EXPRESS="2m";
}