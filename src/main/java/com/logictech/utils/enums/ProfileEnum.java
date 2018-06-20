package com.logictech.utils.enums;

/**
 * Created by wb-jin@logictech.cn on 2017/5/25.
 */
public enum ProfileEnum {
	
    /**
     * 生产
     */
    PRO_ENV("pro"),
    /**
     * 测试
     */
    TEST_ENV("test"),
    /**
     * 开发
     */
    DEV_ENV("dev");
	

	
	
    private String value;

    ProfileEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
