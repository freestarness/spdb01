package com.logictech.utils.enums;

public enum CommonsEnum {
	
	 /**
     * 导入积分状态（未确认）
     */
    IMPORT_POINT_STATUS_NO(0),
    /**
     * 导入积分状态（已确认）
     */
    IMPORT_POINT_STATUS_YES(1),
    /**
     * 导入积分状态（已分配）
     */
    IMPORT_POINT_STATUS_DRI(3),
	
    
    
	/**
	 * 异常订单
	 */
    ORDER_STATUS_ERROR(0),
    /**
	 * 兑入成功(用利市兑入京东产品)
	 */
    ORDER_STATUS_IMP_SUCCESS(1),
    /**
   	 * 兑出成功（用京东产品兑入利市）
   	 */
    ORDER_STATUS_OUT_SUCCESS(2),
	/**
	 * 退款成功
	 */
    ORDER_STATUS_REFUND_SUCCESS(3),
    /**
	 * 待使用订单
	 */
    ORDER_STATUS_NO_PAID(4),
    /**
     * 订单进行中
     */
    ORDER_STATUS_HANDLING(5),

    /**
     * 激活确认 
     */
    INTEGRAL_CHANGE_CONFIRM("1"),
    /**
     * 积分兑出
     */
    INTEGRAL_CHANGE_OUT("2"),
    /**
     * 积分兑入
     */
    INTEGRAL_CHANGE_IMPORT("3"),
    /**
     * 积分退回
     */
    INTEGRAL_CHANGE_REFUND("4"),
    /**
     * 分配获得
     */
    INTEGRAL_CHANGE_DISTRIBUTION("5"),
    /**
     * 积分转赠
     */
    INTEGRAL_CHANGE_DONATION("6"),
    /**
     * 积分获赠
     */
    INTEGRAL_CHANGE_RECEIVE("7"),
    /**
     * 使用者分配
     */
    INTEGRAL_CHANGE_DISTRIBUTE("6"),
    
    /**
	 * 操作成功
	 */
	SUCCESS(1),
    /**
     * 用户类型
     */
    USER_TYPE("wechatUser");
	
	private String value;
	
	private Integer result;
	
	CommonsEnum(String value) {
        this.value = value;
    }
	CommonsEnum(Integer result) {
        this.result = result;
    }
    public String getValue() {
        return this.value;
    }
    public Integer getResult() {
        return this.result;
    }
}
