package com.logictech.service;

import com.logictech.entity.dto.SysToken;
import com.logictech.mapper.SysTokenMapper;
import com.logictech.utils.DateUtils;
import com.logictech.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过Redis存储和验证token的实现类
 *
 * @author ScienJus
 * @date 2015/7/31.
 */
@Service
public class CommonService {
    @Value("${app.aes-key-path}")
    private String aesKeyPath;
    @Value("${app.aes-iv-path}")
    private String aesIvPath;


    @Autowired
    private SysTokenMapper tokenMapper;
    private final String WECHAT_USER = "wechatUser";;

    public SysToken createToken(Integer userId) {
        // 使用uuid作为源token
        String token = StringUtils.createToken(true);
        SysToken model = new SysToken();
        model.setUserId(userId);
        model.setAccessToken(token);
        // 存储到并设置过期时间
        model.setExpiration(System.currentTimeMillis() / 1000L);
        tokenMapper.insert(model);
        return model;
    }

    public SysToken getToken(String authentication) {
        if (authentication == null) {
            return null;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(aesKeyPath))
                    .parseClaimsJws(authentication).getBody();
            SysToken model = new SysToken();
            model.setUserId(Integer.parseInt(claims.getSubject()));
            model.setAccessToken(authentication);
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkToken(SysToken model) {
        if (model == null) {
            return false;
        }
        String token = tokenMapper.checkToken(model.getId(), model.getAccessToken());
        if (token == null || !token.equals(model.getAccessToken())) {
            return false;
        }
        // 如果验证成功，说明此用户进行了一次有效操作，延长token的过期时间
        // token 过期时间
        Date tokenExpired = DateUtils.getTokenExpired();
        // jwt 生成
//        String compactJws = Jwts.builder().setSubject(String.valueOf(model.getId()))
//                .setAudience(null).setExpiration(tokenExpired)
//                .signWith(SignatureAlgorithm.HS512, aesKeyPath).compact();
        model.setExpiration(tokenExpired.getTime());
        tokenMapper.updateByPrimaryKey(model);
        return true;
    }

    /**
     * 延长token时间
     *
     * @param model
     * @return
     */
    public Map<String, Object> prolongToken(SysToken model) {
        Map<String, Object> result = new HashMap<String, Object>();
        if (model == null) {
            result.put("result", false);
            return result;
        }
        // token 过期时间
        Date tokenExpired = DateUtils.getTokenExpired();
        // jwt 生成
        String compactJws = Jwts.builder().setSubject(String.valueOf(model.getUserId()))
                .setAudience(null).setExpiration(tokenExpired)
                .signWith(SignatureAlgorithm.HS512, aesKeyPath).compact();
        model.setExpiration(tokenExpired.getTime());
        model.setAccessToken(compactJws);
        tokenMapper.updateByPrimaryKey(model);
        result.put("result", true);
        result.put("compactJws", compactJws);
        result.put("tokenExpired", tokenExpired);
        return result;
    }

    /**
     * 获取随机验证码
     *
     * @return
     */
    private String getVerificationCode() {
        // 随机数
        SecureRandom secRandom = new SecureRandom();
        return String.valueOf(secRandom.nextInt(900000) + 100000);
    }


}
