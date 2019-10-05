package org.uppower.project.cashiermanagesystem.config;

import cn.windyrjc.security.common.UrlMatcherRegistry;
import cn.windyrjc.security.common.UrlStrategyMatcher;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class DefaultPermissionUrlConfig implements UrlStrategyMatcher {


    @Override
    public void handleUrl(@NotNull UrlMatcherRegistry urlMatcherRegistry) {
        urlMatcherRegistry.ignoreUrl(
                "/static/**",
                "/static/editor-app/**",
                "/favicon.ico",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/csrf",
                "/webjars/**",
                "/swagger-resources/**");
    }
}
