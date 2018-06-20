package com.logictech.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/** Another JSON Serialize & Deserialize Library */
public class JacksonUtils {
    private static ObjectMapper mapper = new ObjectMapper();
    private final static Log log = LogFactory.getLog(JacksonUtils.class);
    
    public static String serialize(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception ex) {
            log.error("Error when serializing object to string.", ex);
        }
        return null;
    }

    /**
     * pretty print enabled, for debug purpose.
     * @param obj
     * @return
     */
    public static String beautifulSerialize(Object obj) {
        try {

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception ex) {
            log.error("Error when serializing object to string.", ex);
        }
        return null;
    }

    public static <T> T deserialize(final String json, Class<T> classofT) {
        try {

            return mapper.readValue(json, classofT);
        } catch (Exception ex) {
            log.error("Error when deserializing json: " + json, ex);
        }
        return null;
    }
    
    /**
     * bean 转换成JSON
     * 
     * @param bean 对象参数
     * @return 转换后的JSON字符串
     */
    public static String bean2json(Object bean) {
        return JSONObject.fromObject(bean).toString();
    }

    /**
     * list 转换成JSON
     * 
     * @param list 需要转换的list
     * @return 转换后的JSON字符串
     */
    public static String list2json(List<?> list) {
        return JSONArray.fromObject(list).toString();
    }
    
    
    /**
     * string 转json
     * 
     * @param param
     * @return
     */
    public static JSONObject StringTOJson(String param) {
        if (param != null && param != "") {
            //          param = param.substring(1, param.length() -1);
            JSONObject jasonObject = JSONObject.fromObject(param);
            return jasonObject;
        }
        else {
            return null;
        }
    }

    /**
     String j = "{\"result\":0,\"msg\":\"msg\",\"info\":\"info_here\"}";
     JsonResp<String> rsp = JacksonUtils.deserializeGeneric(j, new TypeReference<JsonResp<String>>() { });
     System.out.print(rsp.getInfo());

     This code will print `info_here`
     */
    public static <T> T deserializeGeneric(final String json, TypeReference<T> classofT) {
        try {
            return mapper.readValue(json, classofT);
        } catch (Exception ex) {
            log.error("Error when deserializing json: " + json, ex);
        }
        return null;
    }
    
    
    /**
     * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如： {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {}, ...]}
     * 
     * @param jsonString
     * @param clazz
     * @param map 集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" : Bean.class)
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Object getDTO(String jsonString, Class clazz, Map map) {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSONObject.fromObject(jsonString);
        }
        catch (Exception e) {
            log.error(e);
        }
        return JSONObject.toBean(jsonObject, clazz, map);
    }
}
