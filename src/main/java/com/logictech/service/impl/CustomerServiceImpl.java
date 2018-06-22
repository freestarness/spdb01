package com.logictech.service.impl;

import com.logictech.entity.dto.CustomerInfo;
import com.logictech.entity.dto.UserInfo;
import com.logictech.entity.so.AppException;
import com.logictech.mapper.CustomerInfoMapper;
import com.logictech.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static com.logictech.config.MessageConfig.get;

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
    public CustomerInfo initCustomer(Integer customerId) throws Exception {
        return customerInfoMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public List<CustomerInfo> listCustomer(String customerCode,UserInfo userInfo) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerCode(customerCode);
        customerInfo.setLogId(userInfo.getId());
        return customerInfoMapper.listByCustomerCode(customerInfo);
    }

    @Override
    public boolean addCustomer(CustomerInfo customerInfo, UserInfo userInfo) {
        customerInfo.setLogId(userInfo.getId());
        customerInfo.setDeleted("0");
        customerInfo.setCreateTime(new Date());
        customerInfo.setCreateUser(userInfo.getUserName());
        customerInfo.setUpdateTime(new Date());
        customerInfo.setUpdateUser(userInfo.getUserName());
        if(null==customerInfo.getOpenDate()){
            customerInfo.setOpenDate(0);
        }
        if(customerInfoMapper.insert(customerInfo)==0){
            throw new AppException(get("EM0003"));
        }
        return true;
    }

    @Override
    public boolean modifyCustomer(CustomerInfo customerInfo, UserInfo userInfo) {
        customerInfo.setUpdateTime(new Date());
        customerInfo.setUpdateUser(userInfo.getUserName());
        if(customerInfoMapper.updateByPrimaryKeySelective(customerInfo)==0){
            throw new AppException(get("EM0004"));
        }
        return true;
    }

    @Override
    public boolean deleteCustomer(CustomerInfo customerInfo , UserInfo userInfo) {
        customerInfo.setDeleted("1");
        if(customerInfoMapper.updateByPrimaryKeySelective(customerInfo)==0){
            throw new AppException(get("EM0005"));
        }
        return true;
    }
}
    