# EMCodeScan
用来扫描代码的小工具（使用命令执行）</br>


将test下的1.txt和result.txt 拷贝到需要扫描的代码路径下</br>

命令：java -jar test3.jar test C:\Users\Administrator\Desktop\test\（路径要以“\”结尾）</br>

result.txt中存放的是扫描结果：</br>


68,'Denial of Service    //第68条漏洞规则，漏洞名称</br>

main.java                //存在漏洞的文件名</br>

C:\Users\Administrator\Desktop\test\test3\src\main\java\com\scan\codeAudit\main\main.java  //存在漏洞的文件的文件路径</br>

56                       //文件中的第56行存在漏洞</br>



1.txt中存放的是扫描规则，大家可以按照我的格式进行修改</br>


