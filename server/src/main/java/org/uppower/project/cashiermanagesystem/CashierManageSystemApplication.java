package org.uppower.project.cashiermanagesystem;

import cn.windyrjc.security.core.service.TokenService;
import cn.windyrjc.security.web.annotation.EnableSimpleCors;
import cn.windyrjc.security.web.annotation.EnableWindySecurity;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableSimpleCors
@EnableWindySecurity(service = TokenService.REDIS)
@MapperScan(basePackages = "org.uppower.project.cashiermanagesystem.dao")
public class CashierManageSystemApplication {

    public static final long PAGESIZE = 10;

    public static final String BUCKET_NAME = "cashier";

    // 在某配置类中添加如下内容
    // 监听的http请求的端口,需要在application配置中添加http.port=端口号  如80
    @Value("${http.port}")
    Integer httpPort;

    //正常启用的https端口 如443
    @Value("${server.port}")
    Integer httpsPort;

    public static void main(String[] args) {
        SpringApplication.run(CashierManageSystemApplication.class, args);
    }

    //拦截所有请求
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    //配置http转https
    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //Connector监听的http的端口号
        connector.setPort(httpPort);
        connector.setSecure(false);
        //监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(httpsPort);
        return connector;
    }

//    @Bean
//    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
//        return factory -> factory.setPort(httpsPort);
//    }
}
