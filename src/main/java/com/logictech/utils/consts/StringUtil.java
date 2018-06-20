package com.logictech.utils.consts;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * String 工具类
 * @author caspar.chen
 * @version 1.0
 * 
 */
public class StringUtil {
	/**
	 * 获取baseUrl
	 * @create: 2016/08/01 by zht-pan
	 * @modify:
	 */
	public static  String getBaseURL(HttpServletRequest request){
		String path = request.getContextPath();
		String baseUrl = request.getScheme() + "://"
				+ request.getServerName() +":" 
				+ request.getServerPort() +  path + "/";
 		return baseUrl;
	}

	public static boolean isEmpty(Object value) {
		return (value == null || "".equals(value));
	}

	public static boolean isNotEmpty(Object value) {
		return (!isEmpty(value));
	}

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.toUpperCase().replace("-", "");
	}

}
