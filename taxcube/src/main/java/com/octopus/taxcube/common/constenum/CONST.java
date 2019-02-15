package com.octopus.taxcube.common.constenum;

public class CONST {

    public static final String WX_ERROR = "errcode";
    public static final int ERROR_CODE_FAILED    = 10000;
    public static final int ERROR_CODE_NOT_FOUND = 10404;
    public static final int ERROR_CODE_STATE     = 10400;
    public static final int ERROR_CODE_OK        = 00000;
    public static final int ERROR_CODE_EXIST     = 10300;

    public static final String PERIOD_TYPE_NONE = "NONE";
    public static final String STATUS_DOING = "DOING";

    public static final String PAY_TYPE_MONEY = "MONEY";
    public static final String PAY_TYPE_CREDIT = "CREDIT";
    public static final String STATUS_PAYED = "PAYED";
    public static final String OFFER_TYPE_STAR = "STAR";
    public static final String OFFER_TYPE_NORMAL = "NORMAL";


    public enum RESULT{
        /**
         * 成功
         */
        CODE_YES("0"),
        /**
         * 失败
         */
        CODE_NO("-1"),
        /**
         * 失败msg
         */
        MSG_YES("操作成功"),
        /**
         * 失败msg
         */
        MSG_NO("操作失败");
        private String value;

        private RESULT(String value){
            this.value=value;
        }
        public String getValue(){
            return value;
        }
    }

    public static final String LOGIN_METHOD_SYSTEM = "sys";    //系统用户登录
    public static final String LOGIN_METHOD_WECHAT = "wechat"; //微信用户登录
    public static final String LOGIN_METHOD_SMS    = "sms";    //短信登录

    public static final String STATUS_ENABLE  = "ENABLE";
    public static final String STATUS_DISABLE = "DISABLE";
    public static final String STATUS_QUOTATION_INPROGRESS = "INPROGRESS";  //报价单
    public static final String STATUS_DISCARD   = "DISCARD";    //已废弃
    public static final String STATUS_NOTPAY    = "NOTPAY";     //未支付
    public static final String STATUS_NOTDISTRIBUTE = "NOTDISTRIBUTE"; //未分配业务员
    public static final String STATUS_SERVING   = "SERVING";    //服务中
    public static final String STATUS_DONE      = "DONE";       //已完成
    public static final String STATUS_APPLYING = "APPLYING";
    public static final String STATUS_OFFLINE = "OFFLINE";
    public static final String STATUS_ONLINE = "ONLINE";
    public static final String STATUS_NOTSET = "NOTSET";
    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_REFUSE = "REFUSE";

    public static final String ACCOUNT_TYPE_CONSUMER = "CONSUMER";
    public static final String ACCOUNT_TYPE_EMPLOYEE = "EMPLOYEE";
    public static final String ACCOUNT_TYPE_SYSADMIN = "SYSADMIN";
    public static final String ACCOUNT_TYPE_ADMIN    = "ADMIN";

    public static final String PRICE_TYPE_FIXED = "FIXED";

    public static final String CREDIT_TYPE_SEASON = "SEASON";

    public static final String REASON_TYPE_CONSUME = "CONSUME";
    public static final String REASON_TYPE_EXCHANGE = "EXCHANGE";
    public static final String REASON_TYPE_REFUND = "REFUND";
}
