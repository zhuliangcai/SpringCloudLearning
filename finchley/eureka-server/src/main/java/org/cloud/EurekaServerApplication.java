package org.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/10/19 17:29
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication  {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
