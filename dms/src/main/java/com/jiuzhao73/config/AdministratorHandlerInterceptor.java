package com.jiuzhao73.config;

import com.jiuzhao73.constant.Constants;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdministratorHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object aid = request.getSession().getAttribute(Constants.ADMIN_SESSION);
        if (aid == null) {
            request.setAttribute(Constants.MESSAGE_SESSION, "没有登入，请先登录");
            return false;
        }
        return true;
    }
}
