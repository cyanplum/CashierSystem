package org.uppower.project.cashiermanagesystem;

import cn.windyrjc.security.core.service.TokenService;
import cn.windyrjc.security.web.annotation.EnableSimpleCors;
import cn.windyrjc.security.web.annotation.EnableWindySecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSimpleCors
@EnableWindySecurity(service = TokenService.REDIS)
public class CashierManageSystemApplication {

    public static final long PAGESIZE = 10;

    public static void main(String[] args) {
        SpringApplication.run(CashierManageSystemApplication.class, args);
    }

}
