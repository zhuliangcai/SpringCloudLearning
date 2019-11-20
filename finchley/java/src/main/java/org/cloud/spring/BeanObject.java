package org.cloud.spring;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/11/13 9:48
 */
public class BeanObject implements FactoryBean<MyBean> {

    @Override
    public MyBean getObject() throws Exception {
        return new MyBean();
    }

    @Override
    public Class<?> getObjectType() {
        return MyBean.class;
    }
}

class MyBean{

}

class ABean{
    private String sql;
    private String chinese;
    private String nochinese;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getChinese() {
        return chinese;
    }

    public void setNochinese(String nochinese) {
        this.nochinese = nochinese;
    }

    public String getNochinese() {
        return nochinese;
    }
}