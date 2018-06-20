package com.logictech.service.impl;

import com.logictech.entity.dto.UserInfo;
import com.logictech.entity.so.AppException;
import com.logictech.mapper.UserInfoMapper;
import com.logictech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.logictech.config.MessageConfig.get;

/**
 * @author John Doe
 * @since 2018/06/20
 */
@Service
@Transactional(rollbackFor = AppException.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public boolean checkAdminUser(UserInfo userInfo) {
        UserInfo returnUserInfo = userInfoMapper.selectByUserInfo(userInfo);
        if(null == returnUserInfo){
            throw new AppException(get("EM0002"));
        }
        return true;
    }
}
    