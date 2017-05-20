package com.scan.codeAudit.bean;

/**
 * Created by dly on 16/8/8.
 */
public class FileContent {
    private int id;
    private String fileName;
    private String fileContent;
    private String filePath;
    private String fileImport;
    private String projectName;

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

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileImport() {
        return fileImport;
    }

    public void setFileImport(String fileImport) {
        this.fileImport = fileImport;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
