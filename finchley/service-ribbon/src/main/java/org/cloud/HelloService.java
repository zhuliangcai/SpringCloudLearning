package org.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name) {
        System.out.println("this is ribbon");
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }


}