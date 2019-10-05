package org.uppower.project.cashiermanagesystem.config;

import cn.windyrjc.security.web.config.selector.WindySecurityConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
public class SecurityConfig implements WindySecurityConfig {

    @Override
    public void configure(@NotNull HttpSecurity httpSecurity) {
        try {
            httpSecurity.headers().frameOptions().disable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
