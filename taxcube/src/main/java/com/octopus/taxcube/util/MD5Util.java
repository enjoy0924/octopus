package com.octopus.taxcube.util;

import java.security.MessageDigest;

/**
 * Created by G_dragon on 2016/11/25.
 */
public class MD5Util {

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    private static final String salt = "`1234567890)(*&^%$#@!~";
    private static final String NULL_STR = "NULL";

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin){
        return MD5Encode(origin,"UTF-8");
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    public static String GetMD5Code(String password) {
        if(password == null || password.isEmpty()){
            password = NULL_STR;
        }
        String s = null;
        password += salt;
        byte[] source = password.getBytes();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            // MD5的计算结果是一个128位的长整数
            byte tmp[] = md.digest();
            // 用字节表示就是 16 个字节
            // 每个字节用 16 进制表示的话，使用两个字符，所以表示成16进制需要32个字符
            char str[] = new char[16 * 2];
            //表示转换结果中对应的字符位置
            int k = 0;
            //从第一个字节开始，对MD5的每一个字节转换成16进制字符的转换
            for (int i = 0; i < 16; i++) {
                //取第i个字节
                byte byte0 = tmp[i];
                // 取字节中高4位的数字转换,'>>>'为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 >>> 4 & 0xf].charAt(0);
                // 取字节中低4位的数字转换
                str[k++] = hexDigits[byte0 & 0xf].charAt(0);
            }
            //换后的结果转换为字符串
            s = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

}
