package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzw
 * @Description json操作类
 * @date 2019-04-07  18:34:43
 **/
public class JsonUtil {
    /*
     * @author lzw
     * @Description 将数据封装成JSON串
     * @params [code, msg, data]
     * @return java.lang.String
     * @date 2019/4/7 19:00
     **/
    public static String formatJsonString(String code,String msg,Object data){
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("code",code);
        hashMap.put("msg",msg);
        hashMap.put("data", data);
        return JSON.toJSONString(hashMap);
    }
    /*
     * @author lzw
     * @Description 获取json里面指定的数据对象
     * @params [params, JSONString]
     * @return java.lang.Object
     * @date 2019/4/7 18:59
     **/
    public static <T>Object jsonStr2data(String params, String JSONString){
        if (params == ""){
            return null;
        }else {
            //通过静态方法parse，将字符串解析为一个Object对象
            //将返回的Object对象强转为一个Map集合
            Map<String,Object> jsonMap = (Map) JSONObject.parse(JSONString);
            return jsonMap.get(params);
        }
    }
}