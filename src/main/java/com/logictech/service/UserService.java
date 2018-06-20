package com.logictech.service;

import com.logictech.entity.dto.UserInfo;


/**
 * @author John Doe
 * @since 2018/06/20
 */
public interface UserService {


    boolean  checkAdminUser(UserInfo userInfo);
}
