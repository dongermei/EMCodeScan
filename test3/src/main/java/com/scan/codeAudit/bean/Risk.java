package com.scan.codeAudit.bean;

import java.util.Date;

/**
 * Created by dly on 16/8/8.
 */
public class Risk {
    private int id;
    private String riskPath;
    private String leakName;
    private String functionName;
    //风险所存在的行数
    private int line;
    //风险所存在的文件路径
    private String filePath;
    //风险所存在的文件名称
    private String fileName;
    //风险所存在的项目名称
    private String projectName;
    //风险所使用的规则
    private String riskRole;
    private String riskContent;
    private int leaderId;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRiskPath() {
        return riskPath;
    }

    public void setRiskPath(String riskPath) {
        this.riskPath = riskPath;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getLeakName() {
        return leakName;
    }

    public void setLeakName(String riskName) {
        this.leakName = riskName;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRiskRole() {
        return riskRole;
    }

    public void setRiskRole(String riskRole) {
        this.riskRole = riskRole;
    }

    public String getRiskContent() {
        return riskContent;
    }

    public void setRiskContent(String riskContent) {
        this.riskContent = riskContent;
    }
}
