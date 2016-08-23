package com.elixir.activiti.domain;

import java.math.BigDecimal;

/**
 * Created by jingyan on 2016/8/23.
 */
public class DemoInfo extends BaseActBusinessBean {

    private String customerName;
    private BigDecimal loanAmount;
    private String loanTime;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(String loanTime) {
        this.loanTime = loanTime;
    }
}
