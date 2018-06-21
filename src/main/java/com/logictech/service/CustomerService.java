package com.logictech.service;

import com.logictech.entity.dto.CustomerInfo;
import com.logictech.entity.dto.UserInfo;

import java.util.List;

/**
 * @author John Doe
 * @since 2018/06/14
 */
public interface CustomerService {

    CustomerInfo getCustomer(String customerCode) throws Exception;

    List<CustomerInfo> listCustomer(String customerCode,UserInfo userInfo);
}
