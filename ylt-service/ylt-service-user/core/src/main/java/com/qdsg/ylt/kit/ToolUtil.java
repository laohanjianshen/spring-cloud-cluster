package com.qdsg.ylt.kit;

import java.util.Random;

/**
 * @ Author     ：yangrb
 * @ Date       ：Created in 14:24 2018/6/15
 * @ Description：常用工具类封装
 * @ Modified By：
 * @Version: $
 */
public class ToolUtil {
    /**
     * 获取随机位数的字符串
     *
     * @author yangrb
     * @Date 2018/6/14 14:09
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
