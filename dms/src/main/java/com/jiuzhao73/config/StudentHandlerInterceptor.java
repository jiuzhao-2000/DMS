package com.jiuzhao73.config;

import com.jiuzhao73.constant.Constants;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object student = request.getSession().getAttribute(Constants.STUDENT_SESSION);
        if (student == null) {
            request.setAttribute(Constants.MESSAGE_SESSION, "没有登入，请先登录");
            return false;
        }
        return true;
    }
}
