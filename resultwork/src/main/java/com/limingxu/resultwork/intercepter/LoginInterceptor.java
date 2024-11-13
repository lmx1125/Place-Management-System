package com.limingxu.resultwork.intercepter;

import com.limingxu.resultwork.util.MyException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.contains("/login") || uri.contains("/register")) {
            return true;
        }
        HttpSession session = request.getSession();
        Object user = session.getAttribute("User");
        String token = request.getHeader("Authorization"); // 从请求头中获取令牌
        String sessionToken = (String) session.getAttribute("token");

        if (user == null || sessionToken == null || !sessionToken.equals(token)) {
            throw new MyException("错误，未登录或登录失效，请重新登录后在执行");
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("end Interceptor");
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
