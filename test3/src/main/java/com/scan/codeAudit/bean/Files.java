package com.scan.codeAudit.bean;

import java.util.Date;

/**
 * Created by dly on 16/8/8.
 */
public class Files {
    private int id;
    private String fileName;
    private String fileTextContent;
    private String filePath;
    private String isReverse;
    private String projectName;

    private Date createdAt;
    private Date updatedAt;
    private int ownerId;



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

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public int getGitFlag() {
        return gitFlag;
    }

    public void setGitFlag(int gitFlag) {
        this.gitFlag = gitFlag;
    }

    public String getScanPeriod() {
        return scanPeriod;
    }

    public void setScanPeriod(String scanPeriod) {
        this.scanPeriod = scanPeriod;
    }

    public int getAutoScan() {
        return autoScan;
    }

    public void setAutoScan(int autoScan) {
        this.autoScan = autoScan;
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    private int leaderId;
    private int gitFlag;
    private String scanPeriod;
    private int autoScan;
    private String codeLanguage;
    private int taskStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileTextContent() {
        return fileTextContent;
    }

    public void setFileTextContent(String fileTextContent) {
        this.fileTextContent = fileTextContent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getIsReverse() {
        return isReverse;
    }

    public void setIsReverse(String isReverse) {
        this.isReverse = isReverse;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
