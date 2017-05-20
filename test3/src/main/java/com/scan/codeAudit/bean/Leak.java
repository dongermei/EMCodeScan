package com.scan.codeAudit.bean;

import java.util.Date;

/**
 * Created by dly on 16/8/8.
 */
public class Leak {
    private int id;
    private String leakName;
    private String leakContent;
    private String leakTestRole;
    private String codeLanguage;
    private int status;
    private Date createdAt;
    private Date updatedAt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeakName() {
        return leakName;
    }

    public void setLeakName(String leakName) {
        this.leakName = leakName;
    }

    public String getLeakContent() {
        return leakContent;
    }

    public void setLeakContent(String leakContent) {
        this.leakContent = leakContent;
    }

    public String getLeakTestRole() {
        return leakTestRole;
    }

    public void setLeakTestRole(String leakTestRole) {
        this.leakTestRole = leakTestRole;
    }

    public String getCodeLanguage() {
        return codeLanguage;
    }

    public void setCodeLanguage(String codeLanguage) {
        this.codeLanguage = codeLanguage;
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

    public void setCreateAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdateAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}