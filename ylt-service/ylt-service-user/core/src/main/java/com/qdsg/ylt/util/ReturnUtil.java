package com.qdsg.ylt.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnUtil {
    private static final Integer SUCCESS_CODE = 1;
    private static final Integer ERROR_CODE = 0;

    private static final String SUCCESS_MSG = "操作成功";
    private static final String ERROR_MSG = "操作失败";

    public static Map<String, Object> returnSuccess(String message){
        Map<String, Object> result = new HashMap<>();
        result.put("success",SUCCESS_CODE);
        result.put("msg",message);
        return result;
    }

    public static Map<String, Object> returnSuccess(String message,Object data){
        Map<String, Object> result = new HashMap<>();
        result.put("success",SUCCESS_CODE);
        result.put("msg",message);
        result.put("data",data);
        return result;
    }

    public static Map<String, Object> returnSuccess(Object data){
        Map<String, Object> result = new HashMap<>();
        result.put("success",SUCCESS_CODE);
        result.put("msg",SUCCESS_MSG);
        result.put("data",data);
        return result;
    }

    public static Map<String, Object> returnError(String message){
        Map<String, Object> result = new HashMap<>();
        result.put("success",ERROR_CODE);
        result.put("msg",message);
        return result;
    }

    public static Map<String, Object> returnError(String message,Object data){
        Map<String, Object> result = new HashMap<>();
        result.put("success",SUCCESS_CODE);
        result.put("msg",message);
        result.put("data",data);
        return result;
    }

    public static Map<String, Object> returnError(Object data){
        Map<String, Object> result = new HashMap<>();
        result.put("success",ERROR_CODE);
        result.put("msg",ERROR_MSG);
        result.put("data",data);
        return result;
    }
}
