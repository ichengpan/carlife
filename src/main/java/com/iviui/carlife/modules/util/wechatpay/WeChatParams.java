package com.iviui.carlife.modules.util.wechatpay;

/**
 * @author: ChengPan
 * @date: 2019/8/28
 * @description: 微信支付需要的一些参数
 */
public class WeChatParams {
    /**
     * 订单金额【备注：以分为单位】
     */
    public String total_fee;
    /**
     * 商品名称
     */
    public String body;
    /**
     * 商户订单号
     */
    public String out_trade_no;
    /**
     * 附加参数
     */
    public String attach;
    /**
     * 会员ID
     */
    public String memberid;

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    @Override
    public String toString() {
        return "WeChatParams{" +
                "total_fee='" + total_fee + '\'' +
                ", body='" + body + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", attach='" + attach + '\'' +
                ", memberid='" + memberid + '\'' +
                '}';
    }
}