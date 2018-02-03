package cn.william.controller;

import cn.william.utils.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by william on 2017/1/13.
 */
public class FTPTest {

    @Test
    public void testFtpClient() throws Exception{
        //创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        //创建ftp连接
        ftpClient.connect("192.168.184.131",21);
        //登录ftp服务器，使用用户名和密码
        ftpClient.login("ftpuser","admin123");
        //上传文件
        //读取本地文件
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Tao\\Desktop\\123.jpg"));
        //设置上传路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        //修改上传文件的格式
        ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
        //第一个参数：服务器端文档名
        //第二个参数：上传文档的inputStream
        ftpClient.storeFile("worldwill.jpg",fileInputStream);
        //关闭连接
        ftpClient.logout();
    }

    @Test
    public void testFtpUtils() throws Exception{
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Tao\\Desktop\\123.jpg");
        FtpUtil.uploadFile("192.168.184.131",21,"ftpuser","admin123","/home/ftpuser/www/images","/2017/10/27","worldwill.jpg",fileInputStream);
    }


}
