package com.iviui.carlife.modules.util.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: ChengPan
 * @date: 2019/9/9
 * @description: 支付宝
 */
@Controller
@RequestMapping("/aliPay/*")
public class AliPay {

    /**
     *
     * @param orderId
     * @param describe
     * @param subject
     * @param payAmount
     * @return
     * @throws AlipayApiException
     */
    public static String getCodeUrl(String orderId,String describe, String subject, String payAmount) throws AlipayApiException {
        /**
         * 账号信息
         */
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GETEWAY_URL, AlipayConfig.APP_ID,
                AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        AlipayTradePrecreateRequest  alipayRequest = new AlipayTradePrecreateRequest();
        //设置回调地址
        alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(orderId);
        model.setSubject(subject);
        model.setTotalAmount(payAmount);
        model.setBody(describe);
        model.setTimeoutExpress(AlipayConfig.TIMEOUT_EXPRESS);
        String returndata = JSONObject.toJSONString(model);
        //设置业务参数
        alipayRequest.setBizContent(returndata);
        /**
         * 通过alipayClient调用API，获得对应的response类
         */
        AlipayTradePrecreateResponse response = alipayClient.execute(alipayRequest);
        String body = response.getBody();
        JSONObject jsonObject = JSONObject.parseObject(body);
        String qr_code = jsonObject.getJSONObject("alipay_trade_precreate_response").getString("qr_code");
        return qr_code;
    }

    //微信支付接口
    @RequestMapping(value = "alipay",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> wxPay(@RequestParam Map<String, String> paramMap, HttpServletResponse response) throws Exception {
        Map<String,Object> map = new HashMap<>();
        if(StringUtils.isBlank(paramMap.get("orderId"))){
            map.put("msg","参数orderId不能为空");
        }
        try {
            String codeUrl = getCodeUrl(paramMap.get("orderId"), "订单描述", "描述", paramMap.get("payAmount"));
            if(StringUtils.isNotBlank(codeUrl)){
                map.put("msg","支付地址获取成功");
                map.put("alipayCode",codeUrl);
            } else {
                map.put("msg","支付地址获取失败");
            }
        } catch (AlipayApiException e){
            map.put("msg","支付地址获取失败");
        } catch (Exception e){
            map.put("msg",e.getMessage());
        }
        map.put("alipayCode","weixin://wxpay/bizpayurl?pr=v3SOQiQ");
        return  map;
    }
}