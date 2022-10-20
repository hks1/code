package com.example.hplus.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class LoggingInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // get all cookies
        // get the session id
        // log session id:
        // log the request path

        String sessionId = null;

        if(null != request.getCookies()){
            for(Cookie cookie : request.getCookies()){
                if("JSESSIONID".equals(cookie.getName())){
                    sessionId = cookie.getValue();
                }
            }
        }
        System.out.println("Incoming request data log: session: "+ sessionId +" at "+ new Date()+ " for " + request.getRequestURI());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("in post handle");
    }
}
