package com.example.ReMap.common.config;

import com.example.ReMap.domain.member.annotation.AuthUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.realm.AuthenticatedUserRealm;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthUserArgumentResolver authUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(authUserArgumentResolver);
    }
}
