package com.scan.codeAudit.bean;

/**
 * Created by dly on 16/8/8.
 */
public class RiskCheck {
    private int id;
    private String isFilter;
    private String riskId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsFilter() {
        return isFilter;
    }

    public void setIsFilter(String isFilter) {
        this.isFilter = isFilter;
    }

    public String getRiskId() {
        return riskId;
    }

    public void setRiskId(String riskId) {
        this.riskId = riskId;
    }
}
