package com.linkknown.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhoupeng
 */
@SpringBootApplication
@ComponentScan("com.linkknown.crm")
@MapperScan(basePackages = {"com.linkknown.crm.mapper"})
@EnableScheduling
public class CrmWebApplication {

    private static Logger logger = LoggerFactory.getLogger(CrmWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CrmWebApplication.class, args);
        logger.info("========== crm_web 服务启动完成 ==========");
    }

}
