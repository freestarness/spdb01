package com.logictech.service.impl;

import com.logictech.entity.dto.CustomerInfo;
import com.logictech.entity.dto.UserInfo;
import com.logictech.entity.so.AppException;
import com.logictech.mapper.CustomerInfoMapper;
import com.logictech.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author John Doe
 * @since 2018/06/14
 */
@Service
@Transactional(rollbackFor = AppException.class)
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Override
    public CustomerInfo getCustomer(String customerCode) throws Exception {
        return customerInfoMapper.selectByCustomerCode(customerCode);
    }

    @Override
    public List<CustomerInfo> listCustomer(String customerCode,UserInfo userInfo) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerCode(customerCode);
        customerInfo.setLogId(userInfo.getId());
        return customerInfoMapper.listByCustomerCode(customerInfo);
    }
}
    