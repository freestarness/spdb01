package com.logictech.service.impl;

import com.logictech.entity.dto.SysToken;
import com.logictech.entity.dto.UserInfo;
import com.logictech.entity.so.AppException;
import com.logictech.mapper.SysTokenMapper;
import com.logictech.mapper.UserInfoMapper;
import com.logictech.service.UserService;
import com.logictech.utils.DateUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.logictech.config.MessageConfig.get;

/**
 * @author John Doe
 * @since 2018/06/20
 */
@Service
@Transactional(rollbackFor = AppException.class)
public class UserServiceImpl implements UserService {
    @Value("${app.aes-key-path}")
    private String aesKeyPath;
    @Value("${app.aes-iv-path}")
    private String aesIvPath;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SysTokenMapper sysTokenMapper;

    @Override
    public Map<String,Object> checkAdminUser(UserInfo userInfo) {
        UserInfo returnUserInfo = userInfoMapper.selectByUserInfo(userInfo);
        if(null == returnUserInfo){
            throw new AppException(get("EM0002"));
        }else{
            //创建TOKEN
            // token 过期时间
            Date tokenExpired = DateUtils.getTokenExpired();
            // jwt 生成
            String compactJws = Jwts.builder().setSubject(String.valueOf(returnUserInfo.getId()))
                    .setAudience(null).setExpiration(tokenExpired)
                    .signWith(SignatureAlgorithm.HS512, aesKeyPath).compact();

            String token = sysTokenMapper.checkToken(returnUserInfo.getId(), null);
            if(StringUtils.isEmpty(token)){
                SysToken sysToken =  new SysToken();
                sysToken.setUserId(returnUserInfo.getId());
                sysToken.setAccessToken(compactJws);
                sysToken.setExpiration(tokenExpired.getTime());
                sysToken.setDeleted("0");
                sysToken.setCreateUser(returnUserInfo.getUserName());
                sysToken.setCreateTime(new Date());
                sysToken.setUpdateUser(returnUserInfo.getUserName());
                sysToken.setUpdateTime(new Date());
                //生成token
                sysTokenMapper.insert(sysToken);
            }else{
                //更新token
                SysToken sysToken =  new SysToken();
                sysToken.setExpiration(tokenExpired.getTime());
                sysToken.setAccessToken(compactJws);
                sysToken.setUserId(returnUserInfo.getId());
                sysToken.setUpdateUser(returnUserInfo.getUserName());
                sysToken.setUpdateTime(new Date());
                sysTokenMapper.updateByUserId(sysToken);
            }
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("userId", returnUserInfo.getId());
            result.put("token", compactJws);
            result.put("tokenExpired", tokenExpired.getTime());
            return result;
        }
    }

    @Override
    public UserInfo getUserInfo(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }
}
    