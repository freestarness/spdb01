package com.logictech.web;

import com.logictech.entity.dto.CustomerInfo;
import com.logictech.entity.dto.UserInfo;
import com.logictech.security.Authorization;
import com.logictech.security.CurrentUser;
import com.logictech.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.logictech.App.logger;
/**
 * @author John Doe
 * @since 2018/06/14
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    /**
     * 获得客户信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getCustomer")
    @ResponseBody
    public CustomerInfo getCustomer(String customerCode) throws Exception {
        logger.info("获得企业客户信息列表");
        return customerService.getCustomer(customerCode);
    }

    /**
     * 初始话客户信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/initCustomer")
    @ResponseBody
    public CustomerInfo initCustomer(Integer customerId) throws Exception {
        logger.info("获得企业客户信息列表");
        return customerService.initCustomer(customerId);
    }
    /**
     * 获取录入公司信息列表
     * @param customerCode
     * @return
     * @throws Exception
     */
    @RequestMapping("/listCustomer")
    @ResponseBody
    @Authorization
    public List<CustomerInfo> listCustomer(@CurrentUser UserInfo userInfo, String customerCode) throws Exception {
        logger.info("获得企业客户信息列表");
        return customerService.listCustomer(customerCode,userInfo);
    }

    /**
     * 添加公司信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/addCustomer")
    @ResponseBody
    @Authorization
    public boolean addCustomer(@CurrentUser UserInfo userInfo, CustomerInfo customerInfo) throws Exception {
        logger.info("添加企业客户信息列表");
        return customerService.addCustomer(customerInfo,userInfo);
    }

    /**
     * 添加公司信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/modifyCustomer")
    @ResponseBody
    @Authorization
    public boolean modifyCustomer(@CurrentUser UserInfo userInfo, CustomerInfo customerInfo) throws Exception {
        logger.info("添加企业客户信息列表");
        return customerService.modifyCustomer(customerInfo,userInfo);
    }

    /**
     * 删除公司信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteCustomer")
    @ResponseBody
    @Authorization
    public boolean deleteCustomer(@CurrentUser UserInfo userInfo, @RequestBody CustomerInfo customerInfo) throws Exception {
        logger.info("添加企业客户信息列表");
        return customerService.deleteCustomer(customerInfo,userInfo);
    }
}
