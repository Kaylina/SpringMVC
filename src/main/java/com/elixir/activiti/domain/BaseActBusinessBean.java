package com.elixir.activiti.domain;

/**
 * Created by jingyan on 2016/8/23.
 */
public abstract class BaseActBusinessBean {

    protected String businessKey;

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
}
