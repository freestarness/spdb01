package com.logictech.service;

import com.logictech.entity.dto.UserInfo;

import java.util.Map;


/**
 * @author John Doe
 * @since 2018/06/20
 */
public interface UserService {


    Map<String,Object> checkAdminUser(UserInfo userInfo);

    UserInfo getUserInfo(Integer id);
}
