package com.octopus.taxcube.common;

public class CONST {

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

    public static final String STATUS_ENABLE = "ENABLE";
    public static final String STATUS_DISABLE= "DISABLE";

    public static final String ACCOUNT_TYPE_CONSUMER = "CONSUMER";
    public static final String ACCOUNT_TYPE_EMPLOYEE = "EMPLOYEE";
    public static final String ACCOUNT_TYPE_SYSADMIN = "SYSADMIN";
    public static final String ACCOUNT_TYPE_ADMIN    = "ADMIN";
}
