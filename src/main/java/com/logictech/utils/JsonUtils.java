package com.logictech.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JsonUtils {
    /**  
     * 从一个JSON 对象字符格式中得到一个java对象  
     *   
     * @param jsonString  
     * @param beanCalss  
     * @return  
     */
    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonString, Class<T> beanCalss) {
        JSON json = JSON.parseObject(jsonString);
        T bean = (T) JSON.toJavaObject(json, beanCalss);
        return bean;
    }
    
    /**
     * Map 转 Bean
     * @param map
     * @param beanCalss
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(Map<String, Object> map, Class<T> beanCalss) {
        String jsonStr = JSON.toJSONString(map);
        T bean = json2Bean(jsonStr, beanCalss);
        return bean;
    }
    /**
     * json 转 Map
     *
     * @param json
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> json2Map(String json) {
        return JSON.parseObject(json, Map.class);
    }

    /**
     * 对象转JSON
     *
     * @param obj
     * @return
     */
    public static String obj2JsonString(Object obj) {
        return JSON.toJSONString(obj);
    }
    /**
     * map 转json 字符串
     * @param map
     * @return
     */
    public static String simpleMapToJsonStr(Map<String ,Object > map){  
        if(map==null||map.isEmpty()){  
            return "null";  
        }  
        String jsonStr = "{";  
        Set<?> keySet = map.keySet();  
        for (Object key : keySet) {  
            jsonStr += "\""+key+"\":\""+map.get(key)+"\",";       
        }  
        jsonStr = jsonStr.substring(1,jsonStr.length()-2);  
        jsonStr += "}";  
        return jsonStr;  
    }  
	
	/**
	 * json 字符串转map
	 * @param str
	 * @return
	 */
	public static Map<String,Object>  getMapData(String str){
		String sb = str.substring(1, str.length()-1);
		 String[] name =  sb.split("\\\",\\\"");
		 String[] nn =null;
		 Map<String,Object> map = new HashMap<String,Object>();
		 for(int i= 0;i<name.length; i++){
			 nn = name[i].split("\\\":\\\"");
			 map.put(nn[0], nn[1]);
		 }
		return map;
	}
}
