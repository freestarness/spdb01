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

    CustomerInfo initCustomer(Integer customerId) throws Exception;

    List<CustomerInfo> listCustomer(String customerCode,UserInfo userInfo);

    boolean addCustomer(CustomerInfo customerInfo, UserInfo userInfo);

    boolean modifyCustomer(CustomerInfo customerInfo, UserInfo userInfo);

    boolean deleteCustomer(CustomerInfo customerInfo, UserInfo userInfo);
}
