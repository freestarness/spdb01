package com.logictech.config;

import com.logictech.entity.dto.SysToken;
import com.logictech.security.Authorization;
import com.logictech.service.CommonService;
import com.logictech.utils.consts.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author JG.Hannibal
 * @since 2017/12/26 17:09
 */
@Component
public class CurrentUserInterceptorConfig extends HandlerInterceptorAdapter {

    @Autowired
    private CommonService service;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String authorization = request.getHeader(CommonConst.LT_HEADER_TOKEN);
        System.out.println("拦截器 中 的 token 为 authorization={}" + authorization);
        SysToken model = service.getToken(authorization);


        //验证token
        if (service.checkToken(model)) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(CommonConst.CURRENT_USER_ID, model.getUserId());
            System.out.println("验证token 成功");
            return true;
        }
//        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            System.out.println("验证token失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }
}
    