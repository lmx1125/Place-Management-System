package com.limingxu.resultwork.intercepter;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@SpringBootConfiguration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(@NotNull InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/user/*");
    }

    public void setLoginInterceptor(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }
}
