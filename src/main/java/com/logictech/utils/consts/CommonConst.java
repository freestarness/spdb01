package com.logictech.utils.consts;

public class CommonConst {

    /**
     * 字符串数字
     */
    public static final String STR_ZERO = "0";
    public static final String STR_ONE = "1";
    public static final String STR_TWO = "2";
    public static final String STR_THREE = "3";
    public static final String STR_FOUR = "4";
    public static final String STR_FIVE = "5";
    public static final String STR_SIX = "6";
    public static final String STR_SEVEN = "7";
    public static final String STR_YES = "Y";
    public static final String STR_NO = "N";
    public static final String STR_ZEROZERO = "00";
    public static final String DATE_FORMAT = "dd";
    public static final String DATE_FORMAT_YMD = "YYYYMMdd";
    public static final String DATE_FORMAT_Y_M_D = "YYYY-MM-dd";
    public static final String STR_CODE = "code";
    public static final String STR_MESSAGE = "message";
    public static final String STR_BEAN = "bean";
    public static final String STR_NOTDELETE = "N";
    
    public static final Integer INT_ZERO = 0; // 0：成功
    public static final Integer INT_ONE = 1; // 1:无数据
    public static final Integer INT_TWO = 2; // 1:数据重复
    public static final Integer INT_FIVE= 5; // 用户登录过期
    public static final Integer INT_SIX = 6; // 没有权限
    public static final Integer INT_SEVEN = 7; // 7:Excel提示信息
    public static final Integer INT_EIGHT = 8; // 8：参数出错
    public static final Integer INT_NINE = 9; // 9:失败
    public static final Integer INT_TEN = 10; // pagesize
    public static final Integer INT_THIRTH_FIVE = 35;
    public static final Integer INT_TWENTY = 20;
    public static final Integer INT_FORTY = 40;
    public static final Integer INT_POINT_REG = 500;
    public static final Integer INT_POINT_INS = 1000;

    public static final Integer DELETED_NO = 0; // 未删除
    public static final Integer DELETED_YES = 1; // 已删除
    public static final Integer PAGESIZE = 2;	//每页显示的条数
    public static final String LT_HEADER_TOKEN = "x-lt-token";
    public static final String CURRENT_USER_ID = "currentUserId";
    public static final String LT_HEADER_OPENID = "currentOpenId";
    
    /**
     * 积分变化类型
     */
    public static final String POINT_TYPE_SIGN = "1";//签到获得
    public static final String POINT_TYPE_INFO = "2";//完善信息
    public static final String POINT_TYPE_EXCHANGE = "3";//兑换使用
    public static final String POINT_TYPE_REBATE = "4";//订单返点
    public static final String POINT_TYPE_INVITE = "5";//邀请获得
    public static final String POINT_TYPE_REGISTER = "6";//用户注册
    public static final String POINT_TYPE_CORRECT = "7";//系统修正
    public static final String POINT_TYPE_INSURANCE = "8";//保单返点
    
    public static final String POINT_STATUS_IN = "1";//积分获得
    public static final String POINT_STATUS_OUT = "2";//积分花费
    /*
     *  保险公司编号
     *  
     *  */
     public static final String COMPANY_NO_DD ="10001";//大地
     public static final String COMPANY_NO_ZA ="10002";//众安
     public static final String COMPANY_NO_AS ="10003";//安盛
    
    
    /**
     * int数字
     */
    public static final Integer INT_EIGHTY = 80;
    public static final Integer INT_TWO_HUNDRED = 200;
    /**
     * 系統返回code
     */
    public static final String RTCD_SUCCESS = "000000";
    public static final String RTCD_UNKNOW_ERROR = "999999";

    /**
     * 物流信息导入地址 卡密信息导入地址
     */
    public static final String IMPORT_LOGISTICS = "/data1/chefu/template/";
   /**
    * 物流信息导入模版下载地址
    */
    public static final String DOWNLOAD_LOGISTICS_LIST = "/data1/chefu/template/logisticsListTemplet.xlsx";

    
    public static final String SYSTEM_CODE = "SYSTEM";
    
    public static final String UD = "undefined";
    /**
     * 1小时
     */
    public static final Long ONE_HOUR = 3600L;
    /**
     * 倍数
     */
    public static final Long THOUSAND = 1000L;
   /*
    *  乐车邦渠道编号
    *  
    *  */
    public static final String CHANEL_NO_LCB ="00001";
    /*
     *  积分获得事由 订单返点
     *  
     *  */
     public static final String POINTS_TYPE ="4";
     /**
      * 订单成功状态
      */
     public static final String ORDER_STATUS_YES = "1";
     /**
      *	非车险返点积分是否发放
      */
     public static final String CREDITS_GRANT_YES = "Y";
     /**
      *	非车险返点积分是否发放
      */
     public static final String CREDITS_GRANT_NO = "N";
   
      /**
       *	记录是否删除
       */
      public static final String DELETE_NO = "N";
      /**
       * 车险无积分发放信息
       */
      public static final String POINTS_ZERO="0";
      /**
       * 车险积分未发放
       */
      public static final String POINTS_ONE="1";
      /**
       * 车险积分已发放
       */
      public static final String POINTS_TWO="2";
      /**
       * 商业险
       */
      public static final String PREMIUMTYPE_BUSSINESS="2";
      /**
       * 交强险
       */
      public static final String PREMIUMTYPE_COMPEL="1";
      /**
       * 车辆基本信息无
       */
      public static final String BASE_CONFIRM_ZERO="0";
      /**
       * 车辆基本信息未确认
       */
      public static final String BASE_CONFIRM_ONE="1";
      /**
       * 车辆基本信息已确认
       */
      public static final String BASE_CONFIRM_TWO="2";
      
      /** 
       * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173 
       * 
       **/  
      public static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|53|7[37]|8[019])\\d{8}$)|(?:^(?:\\+86)?1700\\d{7}$)";
      
      
      /** 
       * 中国移动号码格式验证 
       * 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184 
       * ,187,188,147,178,1705 
       *  
       **/  
      public static final String CHINA_MOBILE_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(?:^(?:\\+86)?1705\\d{7}$)"; 

      /** 
       * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709,175 
       * 
       **/  
      public static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[5]|5[56]|7[56]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[7-9]\\d{7}$)";
      
      /**
       * 验证11位手机号码
       */
      public static final String CHINA_PHONE_PATTERN = "^[0-9]\\d{10}$";
      
      /**
       * 商品发货通知微信消息模版ID
       */
      public static final String GOODS_DELIVER_TEMPLATEID = "GblrpedbjHKvzuouEz5u7uRW2sRtRHGURaHlqxtfofU";
      /**
       * 商品兑换成功通知微信消息模版ID
       */
      public static final String GOODS_EXCHANGE_TEMPLATEID = "HhI7JqEIB0_LGXTfnfWW8WJYcqoLKcc7BmW7A0U9pjU";
      /**
       * 投保成功通知微信消息模版ID
       */
      public static final String INSURANCE_SUCCESS_TEMPLATEID = "mwtr5XjdNOQtt0QzHHuXtV9d0QN9CzfM_YL0Ln8ytDQ";
      /**
       * 延保审核结果提醒消息模版ID
       */
      public static final String EXTENSION_INSURANCE_CHECK = "1K4bRndKH0VTYoMhCGsBENCc5Lao_PU1FSEM0loxMLo";
      /**
       * 延保审核提醒消息模版ID
       */
      public static final String EXTENSION_INSURANCE_REMIND = "7KQd1MRdt7LdbLYAYPGHwoi3Hx0oljCR7ctsJ10_Oqw";
}
