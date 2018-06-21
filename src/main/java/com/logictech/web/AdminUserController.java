package com.logictech.web;

import com.logictech.entity.dto.UserInfo;
import com.logictech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.logictech.App.logger;

/**
 * @author John Doe
 * @since 2018/06/14
 */
@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;


    /**
     * 用户登陆
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(UserInfo userInfo) throws Exception {
        logger.info("获得企业客户信息列表");
        return userService.checkAdminUser(userInfo);
    }
}
