package com.logictech.service;

import com.logictech.entity.dto.CustomerInfo;

/**
 * @author John Doe
 * @since 2018/06/14
 */
public interface CustomerService {

    CustomerInfo getCustomer(String customerCode) throws Exception;
}
