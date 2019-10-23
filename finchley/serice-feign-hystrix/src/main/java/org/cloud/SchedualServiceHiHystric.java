package org.cloud;

import org.springframework.stereotype.Component;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/10/19 18:45
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "Sorry "+name;
    }
}
