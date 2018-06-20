package com.logictech.utils;

import java.util.Map;

/**
 * 校验京东签名
 * @author lt
 *
 */
public class RtmSignUtil {
	//京东key
//	@Value("${motivator.application.jd-key}")
	public static String jdKey = "27392a6eb22d1c2c1c71a20558c73f5d";
	//京东客户端编号
//	@Value("${motivator.application.client-id}")
    public static String clientId = "26";
	/**
	 * 积分查询 和登录加密 的 签名
	 * @return
	 */
	public static String getSignByUser(String uid,String timestamp){
		//带签名的字符串
		String str = clientId+"#"+uid+"_"+jdKey+"#"+timestamp;
		
		return MD5Util.toMD5(str);
	}
	/**
	 * 增加积分、扣减积分、退还积分 签 的名
	 * @return
	 */
	public static String getSignByOrder(Map<String,String> params){
		String uid = (String) params.get("uid");
		String rtmOrderNum = (String) params.get("rtmOrderNum");
		String credits = (String) params.get("credits");
		String timestamp = (String) params.get("timestamp");
		String str = clientId+"#"+uid+"_"+rtmOrderNum+"_"+credits+"#"+jdKey+"#"+timestamp;
		return MD5Util.toMD5(str);
	}
	/**
	 * 获得查询订单明细的签名
	 * @return
	 */
	public static String getSignByQuery(String rtmOrderNum,String timestamp){
		String str = clientId+"#"+rtmOrderNum+"_"+jdKey+"#"+timestamp;
		return MD5Util.toMD5(str);
	}

}
