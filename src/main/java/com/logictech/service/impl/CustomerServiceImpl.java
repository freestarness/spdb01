package com.logictech.service.impl;

import com.logictech.entity.dto.CustomerInfo;
import com.logictech.entity.so.AppException;
import com.logictech.mapper.CustomerInfoMapper;
import com.logictech.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
    