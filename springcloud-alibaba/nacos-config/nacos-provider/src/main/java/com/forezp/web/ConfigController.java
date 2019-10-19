package com.forezp.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by forezp on 2019/5/12.
 */

@RestController
@RefreshScope
public class ConfigController {

    @Value("${username:lily}")
    private String username;
    @Value("${username1:lily}")
    private String username1;
    @Value("${a:x}")
    private String a;
    @Value("${b:y}")
    private String b;
    @RequestMapping("/username")
    public String get() {
        System.out.println(username);
        System.out.println(username1);
        System.out.println(a);
        System.out.println(b);
        return username;
    }
}