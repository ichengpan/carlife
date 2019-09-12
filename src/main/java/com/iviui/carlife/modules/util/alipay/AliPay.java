package com.iviui.carlife.modules.util.alipay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import org.apache.commons.lang3.StringUtils;
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
     * @param orderId 订单号
     * @param subject 订单描述
     * @param payAmount 订单总金额
     * @return 支付路径
     * @throws AlipayApiException
     */
    public String getCodeUrl(String orderId, String subject, String payAmount) throws AlipayApiException {
        /**
         * 账号信息
         */
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.DEV_GETEWAY_URL, AlipayConfig.DEV_APP_ID,
                AlipayConfig.MERCHANT_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGN_TYPE);
        //创建API对应的request类
        AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
        //设置回调地址
        alipayRequest.setNotifyUrl(AlipayConfig.NOTIFY_URL);
        // 封装请求支付信息
        Map<Object, Object> data = new HashMap<>();
        //订单号 必填
        data.put("out_trade_no",orderId);
        //订单总金额 单位元
        data.put("total_amount",payAmount);
        //订单标题
        data.put("subject",subject);
        //门店编号
        data.put("store_id","NJ_001");
        //该订单最晚付款时间 逾期关闭交易
        data.put("timeout_express",AlipayConfig.TIMEOUT_EXPRESS);
        String paramData = JSONObject.toJSONString(data);
        //设置业务参数
        alipayRequest.setBizContent(paramData);
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
            map.put("code",0);
        }
        try {
            String codeUrl = getCodeUrl(paramMap.get("orderId"),  paramMap.get("subject"), paramMap.get("payAmount"));
            if(StringUtils.isNotBlank(codeUrl)){
                map.put("msg","支付地址获取成功");
                map.put("code",1);
                map.put("alipayCode",codeUrl);
            } else {
                map.put("msg","支付地址获取失败");
                map.put("code",0);
            }
        } catch (AlipayApiException e){
            map.put("msg","支付地址获取失败");
            map.put("code",0);
        } catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("code",0);
        }
        return  map;
    }
}