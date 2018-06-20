package com.logictech.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class OrderUtils {
	public static String pattern="yyyyMMdd";  
    public static SimpleDateFormat formatter = new SimpleDateFormat(pattern);  
    public static SimpleDateFormat formatter2 = new SimpleDateFormat("HHmmss");  
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);  
    public static Long  count = 0L;
    public static DateTimeFormatter getDateTimeFormatter(){  
       return dateFormatter;   
    }  
    /** 
     * 获取时间yyyyMMdd
     *  
     * @return 
     */  
    public static String  getChnlDate() {  
     SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");  
     return formatter.format(new Date());  
    }  
    /** 
     * 获取时间HHmmss
     *  
     * @return 
     */  
    public static String  getChnlTime() {  
     SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");  
     return formatter.format(new Date());  
    }  
    /** 
     * 获取订单号
     *  
     * @return 
     */  
    public static String  getOrderNo() {  
		if(count == 999){
			count =1L;
		}
		count++;
	     StringBuffer sbf = new StringBuffer();
	     sbf.append("AXB");
	     sbf.append(getChnlDate());
	     sbf.append(getChnlTime());
	     sbf.append(String.format("%03d", count));
     return sbf.toString();  
    } 
}
