package com.logictech.web;

import com.logictech.entity.dto.CustomerInfo;
import com.logictech.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
