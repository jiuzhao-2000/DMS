package com.jiuzhao73.config;

import com.jiuzhao73.constant.Constants;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MaintainerHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object mid = request.getSession().getAttribute(Constants.MAINTAIN_SESSION);
        if(mid==null){
            request.setAttribute(Constants.MESSAGE_SESSION,"没有登入，请先登录");
            return false;
        }
        return true;
    }
}
