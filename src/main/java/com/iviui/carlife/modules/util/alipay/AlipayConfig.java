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
    public static String DEV_APP_ID = "2016101300676777";
    public static String APP_ID = "2019021763259092";

    /**
     * 商户的私钥,使用支付宝自带的openssl工具生成
     */
    public static String MERCHANT_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCdOKtQ8i/oqxdD41rQBbHWVRfce1NreHSSZ2PophtW5JsG5TaF7CvSzt22FNHABwvshgp51woIPFSxO+wr1zRVz7TliTtzDUy6lj8s7fRL4XJqsr4OYZ2rA9cvbCDcmhUsjUBk/iuj7erKKRcF1nnTnmhxjzveYOpZo/N+nuk+ieyWF28sh2/Au50K5oUA2Bzw2bQjuKeb8YJo97OH+TfD3uPPebMrC30VqMK9RBhIwcfG64S7qYJc5bpXyQAqp9quL0So/LZWr7a7t3EL1zJPskKdLPxlXDKYrnMJflC2TbYC2aH+iBHxDby9ezEHJ1IFH7rd1c8+NNkzqc4MpoJDAgMBAAECggEAFhA20nQ6Tu97o1ZLjzfNXiOw5osUCNftnAIQXjy5R3drXgtanG6pM7s+F+hSCx2yCa5B2BlaWg+FXx3mpbxcptIwM6oPgrTSGLkvAnIeP2vS77BHxaLjSnWOhCQrdGnXINptt/6HR0kDEv2zMmmHzS/EvnL4VtvI521/pTxNxS3CHbG+T0LDpH9vRv5ExXO41RF0DRiiXie9RiZG1dB3k9ObenVboTXWTmcE0QEeKXMxBUPsC7BTTc+2Q5/Rz9YX8xxU5e6yHnItGanKFc8jYEc1jOyHxmdD/ppgMpNyUhwsDdIAF8PNW2kT9rYJkVfNbIdvcwmWaaaifSe+NaSYUQKBgQD7OUd5qiqyQ1WxPfdNtSx2rNG5KMnnOyUKYBhO5KZncSmasYN/mAZVUifd+Qs7bAvT4/supu6Sv9S0rYpxftfldLmjPy1SxPZF+Nwd43sENxL14XdiYwd7UuKxhdu634R+wJols5t+sMRSxWeisQnx03dzK9Mj/lZc28kk8zANiwKBgQCgNd/20T68kGEq5BP+KGO+SxfmFqpKuYjDBpMnLOd/kKoA/opWdroDiyDUEJNn6SvLLcLmjmpMN6J8kUYdyAMRuNNLrHVzvM4UNrWy3bpQPQOtmI0jPQGd6yBhX3WUJ0WF69F7vhTECk1iStuu3XeDM43WVmBEHRLlO2VQEmXlKQKBgH5E7/Pq38LxrSSE7MQWB98uRcs3ASRDyf5JBm7A5hKgAjVKe7mqA6TmwjsDbe3UBK3frORp6we/e33ErA0XoBQocxXmLzWm4dGRn7E2ISKhO9pyFLtS4vPNWTu4+BQZPFMpI1FH0SQAXOztY4WWTlVJ4IP2xw0UTPwnTBZSVRi5AoGAMsgA3nYCobhN5E94+tWIkBezUo4amibm75JU8FTuZ+PNzxTfenqViWv/EtjSMFybMgKmRXgcv13fFgbiJ6xvNJ3V86WNcQsGcyfIPhLD7Iz6bnrtOZ8rtQeDMvoUBcrQ4ZsWwldEs8LkNmSt72ZpbF/Z9HdaehyREbHa8h9yxskCgYEAhgNbGgi+VavwxgyJTNqGxPqebaDNoN/f3M/2BRuekTXCNaGZWlLpfCFeL3eJufZZF8/Y00ro3z/rpSfmuFqAWJQDKBWxQBW3XHwFKGekS0/Gc1StoY1MNaQRSAUajsPeqNYNNmf/i4RCeM0x8Siz4hQWFxs6SSUJM540l7yzlxU=";

    /**
     * 支付宝的公钥，去open.alipay.com对应应用下查看
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsIAwOFi8TvV+2hsvFOWiqTl+AoXuVEmThU8Z87xOuyLv4c3MER1tLSQa+KkJLixkBvGYRNjzAMWDRIasrJJeboBS9FUd9edOdkNbHI+7Zq7IkhkLsxZif3iiZ5GNdFJRmqx0I0nPJ6lEMdux0uZN9Q0R1rGr/g9jG0kBOGcKoD5VhhM8Ky12PufDOLYbPuGBcJSQp1Du0UI5rIExGKmdPOZXpALr43sEc9DwOfv4QeClVJe6Gc5Ekw501RKg1fIuck+v9wPzSgEvp36IqyixZ4mmuWqIAyMaX3gVsg4C+PrtpnEiHJr9FZFei1jlNH32eUe3ZhFfYpjq3A0p8n4WNwIDAQAB";

    /**
     * 支付宝回调地址
     */
    public static String NOTIFY_URL = "http://47.104.107.142/nl_service/systemService/zfbPayCallBack";

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
     * 支付宝正式网关
     */
    public static String GETEWAY_URL="https://openapi.alipay.com/gateway.do";
    /**
     * 支付宝沙箱网关
     */
    public static String DEV_GETEWAY_URL="https://openapi.alipaydev.com/gateway.do";

    /**
     * 超时时间 可空
     */
    public static String TIMEOUT_EXPRESS="2m";
}