package com.scan.codeAudit.main;

import com.scan.codeAudit.bean.Leak;
import com.scan.codeAudit.bean.Risk;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/5/17.
 */
public class main {
    public static void main(String[] args)  {
        String fileName = args[0];
        String gitFlag = "";
        String filePath = args[1];//"C:\\Users\\Administrator\\Desktop\\test\\resources\\";
        System.out.println(fileName);
        System.out.println(gitFlag);
        System.out.println(filePath);
        //用于本地扫描
        File file = new File(filePath);
        List<Risk> result = new ArrayList<Risk>();
        List<File> allFile = new ArrayList<File>();
        //获取规则库位置
        List<Leak> leaks = getRules(filePath);
        System.out.println(leaks.size());
        if(file.isDirectory()){
            try {
                result = findFiles(filePath, fileName, result,allFile,leaks);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        writeFile(filePath,result);
    }

    /**
     *
     * 读取规则库中的规则
     *
     */
    private static List<Leak>  getRules(String path) {
        File file = new File(path+"1.txt");
        List<Leak> leaks = new ArrayList<Leak>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //System.out.println("line " + line + ": " + tempString);
                Leak leak = new Leak();
                String[] stringArr = tempString.split("\',\'");
                if(stringArr.length ==4){
                    leak.setLeakTestRole(stringArr[2]);
                    leak.setCodeLanguage(stringArr[3].substring(0,stringArr[3].length()-1));
                    leak.setLeakName(stringArr[0]);
                    leak.setLeakContent(stringArr[1]);
                    leaks.add(leak);
                }

                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return leaks;
    }

    //递归读取文件下所有文件
    private static List<Risk> findFiles(String path, String projectName, List<Risk> riskResult, List<File> allFile,List<Leak> leaks) throws UnsupportedEncodingException {
        //用来搜索文件
        File[] allPathFile = new File(path).listFiles();
        //  System.out.println(projectName+path);
        //用来存储文件
        // List<File> allFile = new ArrayList<File>();
        //用来存储文件位置
        List<String> allFilePath = new ArrayList<String>();
        //漏洞匹配结果
        //List<Risk> riskResult = new ArrayList<Risk>();
        if (allPathFile != null){
            //System.out.println("下载文件成功,并开始扫描。");
            for(File file:allPathFile) {
                System.out.println(file.getName());
                if (file.isFile()) {
                    allFile.add(file);
                    allFilePath.add(file.getPath());
                    //System.out.println(file.getPath() + file.getName());
                    if(file.getName().equals("pom.xml")) {
                        String fileContent = "";
                        BufferedReader reader = null;
                        //System.out.println("以行为单位读取文件内容，一次读一整行：");
                        try {
                            reader = new BufferedReader(new FileReader(file));
                            //   System.out.println(reader.read());
                            String line;
                            while ((line = reader.readLine()) != null) {
                                fileContent += line;
                            }
                            reader.close();
                            //fileContent = reader.toString();
                            //reader.read();
                        } catch (IOException e) {
                            e.printStackTrace();
                            if(reader != null){
                                try {
                                    reader.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                        String regEx = "";
                        for (Leak leak : leaks) {
                            if(leak.getCodeLanguage().equals("pom")){
                                regEx = leak.getLeakTestRole();
                                Pattern p = Pattern.compile(regEx);
                                Matcher m = p.matcher(fileContent);
                                if (m.find()) {
                                    //System.out.println(m.group(0));
                                    Risk risk = new Risk();
                                    risk.setLeakName(leak.getLeakName());
                                    risk.setRiskPath(file.getPath());
                                    risk.setFilePath(file.getPath());
                                    risk.setFileName(file.getName());
                                    risk.setProjectName(projectName);
                                    risk.setRiskContent(fileContent);
                                    risk.setRiskRole(leak.getLeakTestRole());
                                    riskResult.add(risk);
                                    continue;
                                } else {

                                    continue;
                                }
                            }

                        }
                    } else if (file.getName().contains(".php") || file.getName().contains(".java") || file.getName().contains(".xml") || file.getName().contains(".ini") || file.getName().contains(".jsp") ) {
                        riskResult = readFileByLine(file, projectName, riskResult,leaks);
                    }

                }else if(file.isDirectory() && file.getName().contains("editor")){
                    //      System.out.println(" 使用了第三方插件" + file.getName() );
                    Risk risk = new Risk();
                    risk.setLeakName(new BASE64Encoder().encode((" 使用了第三方插件" + file.getName() ).getBytes()));
                    risk.setRiskPath(file.getPath());
                    risk.setProjectName(projectName);
                    riskResult.add(risk);
                    continue;
                }  else if (file.isDirectory() && (file.getName().contains(".git") || file.getName().contains(".svn") || file.getName().contains("MACOSX") || file.getName().contains("idea"))) {
                    //System.out.printf("暴露" + file.getName() + "文件夹");
                    Risk risk = new Risk();
                    //leak.getLeakName();

                    risk.setLeakName(new BASE64Encoder().encode(("暴露" + file.getName() + "文件夹").getBytes()));
                    risk.setRiskPath(file.getPath());
                    //风险所存在的行数
                    // risk.setLine(line);
                    //风险所存在的文件路径
                    risk.setFilePath(file.getPath());
                    //风险所存在的文件名称
                    risk.setFileName(file.getName());
                    //风险所存在的函数名称
                    // risk.setFunctionName(functoinName);
                    //风险所存在的项目名称
                    risk.setProjectName(projectName);
                    //risk.setLeakName();
                    //风险所检验时用的规则
                    //risk.setRiskRole(leak.getLeakTestRole());
                    //将结果插入数据库,并保存到list中

                    riskResult.add(risk);
                    continue;
                } else if(!file.getName().equals(".idea") && !file.getName().contains("MACOSX")) {
                    findFiles(file.getPath(), projectName, riskResult, allFile,leaks);

                }
            }
        }

        return  riskResult;
    }





    public static String getTypeName(String s){
        String s1=s.substring(s.indexOf(".")+1,s.length());
        s1=s.substring(s.lastIndexOf(".")+1,s.length());
        if(s1.indexOf(".")>0){
            s1=getTypeName(s1);
        }
        else{
            return s1;
        }
        return s1;
    }

    //每查找出来一个java文件,按行读取文件。
    //每查找出来一个java文件,按行读取文件。
    private static List<Risk> readFileByLine(File file, String projectName, List<Risk> riskResult, List<Leak> leaks){
        // List<Risk> riskResult = new ArrayList<Risk>();
        //  List<String> functionNameList = new ArrayList<String>();
        String functoinName = "";
        List<String> importFiles = new ArrayList<String>();
        String importFileName = "";
        BufferedReader reader = null;
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = "";
            int line = 1;
            String fileContent = "";
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                //  System.out.println("line " + line + ": " + tempString);
                fileContent += tempString;
                /**
                 * 匹配当前文件所引入的文件,,,如import
                 * 因为匹配到一种类型的时候,其他类型是一定匹配不到的,漏洞
                 * 除外,所以,采用if,else匹配一次的概念。当匹配结果是
                 * import的文件时,
                 */

                String importFileRole = "(import)\\s*[a-zA-Z0-9_<>.]+\\;";
                Pattern p1 = Pattern.compile(importFileRole);
                Matcher m1 = p1.matcher(tempString);
                if (m1.find()) {
                    importFileName = m1.group(0);
                    importFiles.add(importFileName);
                    //System.out.println("引入文件是:"+ importFileName);
                }
                else{
                    //当匹配的不是导入文件时,尝试匹配函数名,此处函数名有一特殊的地方,
                    //确定不是类名,而是函数名,双重标准去验证。

                    String functionNameRole = "(public|private|protected)\\s*[a-zA-Z0-9_<>]+\\s*+([a-zA-Z0-9_]+)\\(.*\\).*";
                    Pattern p0 = Pattern.compile(functionNameRole);
                    Matcher m0 = p0.matcher(tempString);
                    if (m0.find()) {
                        functoinName = m0.group(0);
                        // System.out.println("当前函数是:"+ functoinName);
                    } else {
                        riskResult = findLeak(file,tempString,line,functoinName,projectName,riskResult,leaks);
                    }

                }


                //对每行内容进行匹配

                line++;
            }
            reader.close();

            Risk risk = new Risk();
            for(Leak leak :leaks){
                if(leak.getCodeLanguage().equals("html"))
                if(fileContent.contains(leak.getLeakTestRole())){
                    risk.setRiskPath(file.getPath());
                    risk.setProjectName(projectName);
                    risk.setCreatedAt(new Date());
                    risk.setUpdatedAt(new Date());
                    risk.setRiskContent(leak.getLeakTestRole());
                    riskResult.add(risk);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return riskResult;

    }


    private static List<Risk> findLeak(File file, String tempString, int line, String functoinName, String projectName, List<Risk> riskResult,List<Leak> leaks) throws UnsupportedEncodingException {

        // leaks = leakService.selectByCodeLanguage("Java");
        // System.out.println(new MimetypesFileTypeMap().getContentType(file.getName()));
        //logger.info(file.getName());
        //logger.info(leaks.size() + tempString);
        String regEx = "";
        // System.out.println(tempString);

        for(Leak leak : leaks){
            //正则表达式
            try {
                if(leak.getCodeLanguage().equals("Java") && (getTypeName(file.getName()).contains("java")  || getTypeName(file.getName()).contains("jsp")))
                {
                    regEx = leak.getLeakTestRole();
                }
                else if(leak.getCodeLanguage().equals("php") && (getTypeName(file.getName()).contains("php") || getTypeName(file.getName()).contains("ini"))){
                    regEx = leak.getLeakTestRole();
                }
                else if(leak.getCodeLanguage().equals("xml") && getTypeName(file.getName()).contains("xml") || getTypeName(file.getName()).contains("xsl") ){
                    regEx = leak.getLeakTestRole();
                }
                else {
                    continue;
                }

               // System.out.println(regEx);
                // regEx = "(select)\\s*[a-zA-Z0-9_<>.*]+\\s*(from)\\s*[a-zA-Z0-9_<>.*]+\\s*(where)";
                //测试时添加的字段
                //使用正则表达式进行匹配
                // System.out.println(regEx);
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(tempString);
                Boolean b = m.find();
                if(b){
                    System.out.println(tempString);
                    System.out.println(regEx);
                }
                //System.out.println(m.groupCount());
                if( b&&!riskResult.contains(tempString) && !tempString.contains("Log::")){
                    System.out.println(m.groupCount());
                    //System.out.println(line + "不存在"+ leak.getLeakName());
                    //   System.out.println(m.group(0));

                        Risk risk = new Risk();
                        //leak.getLeakName();
                        //StringToUnicaode stringToUnicaode = new StringToUnicaode();
                        risk.setLeakName(leak.getLeakName());
                        String riskPath = file.getPath();
                        risk.setRiskPath(file.getPath());
                        risk.setCreatedAt(new Date());
                        risk.setUpdatedAt(new Date());
                        //System.out.println(tempString );

                        //System.out.println(m.group(0));
                        //风险所存在的行数
                        risk.setLine(line);
                        //风险所存在的文件路径
                        risk.setFilePath(file.getPath());
                        //风险所存在的文件名称
                        risk.setFileName(file.getName());
                        //风险所存在的函数名称
                        risk.setFunctionName(functoinName);
                        //风险所存在的项目名称
                        risk.setProjectName(projectName);
                        //风险所检验时用的规则
                        risk.setRiskRole(leak.getLeakTestRole());
                        risk.setRiskContent(tempString);
                        System.out.println(tempString);
                        System.out.println(regEx);
                        //将结果插入数据库,并保存到list中
                        riskResult.add(risk);


                    break;
                }
                else {

                    continue;
                }
            }
            catch (Exception e){
                System.out.println(regEx + e);
            }


        }


        return riskResult;
    }

    /**
     * 匹配到漏洞写文件，写到当前目录下
     */

    public static void writeFile(String filePath ,List<Risk> risks) {
        String str = "";
        for(Risk risk : risks){
            String temp = risk.getLeakName()+"    "+risk.getFileName()+"    "+risk.getFilePath()+"    "+risk.getLine()+"\r\n";
            str = temp + str;
        }
        try {
            FileOutputStream out = new FileOutputStream(filePath + "result.txt"); // 输出文件路径
            out.write(str.getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
