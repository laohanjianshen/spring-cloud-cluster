package com.qdsg.ylt.kit;


import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 14:24 2018/6/15
 * @ Description：MD5加密与验证类
 * @ Modified By：
 * @Version: $
 */
public class MD5Util {
    private final static String HEX_NUMS_STR = "0123456789ABCDEF";

    public static String encrypt(String source) {
        return encodeMd5(source.getBytes());
    }


    /**
     * 密码验证 md5(md5($salt).$pass)
     * @param passwd 用户输入密码
     * @param dbPasswd 数据库保存的密码
     * @param salt 数据库保存的密码盐
     * @return boolean
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static boolean validPasswd(String passwd, String dbPasswd,String salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException{
        byte[] pwIndb =  hexStringToByte(dbPasswd);
        //定义salt

        //创建消息摘要对象
        MessageDigest md = MessageDigest.getInstance("MD5");
        //将盐数据传入消息摘要对象
        md.update(encrypt(salt).getBytes());
        md.update(passwd.getBytes("UTF-8"));
        byte[] digest = md.digest();
        String m = encodeHex(digest);
        if(encodeHex(digest).equals(dbPasswd)){
            //口令匹配相同
            return true;
        }else{
            return false;
        }
    }
    /**
     * 密码:盐 生成器 md5(md5($salt).$pass)
     * @param password 用户输入密码
     * @return salt:password
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String passwordBuilder(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException{

            //定义salt
            String salt = ToolUtil.getRandomString(6);

            //创建消息摘要对象
            MessageDigest md = MessageDigest.getInstance("MD5");

            //将盐数据传入消息摘要对象
            md.update(encrypt(salt).getBytes());
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();

            //生成salt:password字符串返回
            StringBuilder md5 = new StringBuilder();
            md5.append(encodeHex(digest)).append(":").append(salt);
            return md5.toString();
    }
    /**
     * 密码:盐 生成器 md5(md5($salt).$pass)
     * @param message 医院相关信息拼接字符串
     * @return salt:password
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String signBuilder(String message,String salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException{

        //定义salt
//        String salt = ToolUtil.getRandomString(6);

        //创建消息摘要对象
        MessageDigest md = MessageDigest.getInstance("MD5");

        //将盐数据传入消息摘要对象
        md.update(encrypt(salt).getBytes());
        md.update(message.getBytes("UTF-8"));
        byte[] digest = md.digest();

        //生成salt:password字符串返回
        StringBuilder md5 = new StringBuilder();
        md5.append(encodeHex(digest)).append(":").append(salt);
        return md5.toString();
    }
    /**
     * 将16进制字符串转换成数组
     *
     * @return byte[]
     * @author yangrb
     * */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR
                    .indexOf(hexChars[pos + 1]));
        }
        return result;
    }
    private static String encodeMd5(byte[] source) {
        try {
            return encodeHex(MessageDigest.getInstance("MD5").digest(source));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    private static String encodeHex(byte[] bytes) {
        StringBuffer buffer = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            if (((int) bytes[i] & 0xff) < 0x10)
                buffer.append("0");
            buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
        }
        return buffer.toString();
    }





}
