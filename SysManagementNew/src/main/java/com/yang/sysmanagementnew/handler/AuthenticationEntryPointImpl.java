package com.yang.sysmanagementnew.handler;

import com.alibaba.fastjson.JSON;
import com.yang.sysmanagementnew.config.Constants;
import com.yang.sysmanagementnew.domain.Result;
import com.yang.sysmanagementnew.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("认证失败："+authException);
        Result result = new Result(Constants.CODE_401,"用户认证失败请查询登录","");
        String json = JSON.toJSONString(result);
        //处理异常
        WebUtils.renderString(response,json);
    }
}
