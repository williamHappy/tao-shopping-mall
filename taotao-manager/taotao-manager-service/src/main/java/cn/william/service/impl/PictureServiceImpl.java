package cn.william.service.impl;

import cn.william.service.PictureService;
import cn.william.utils.FtpUtil;
import cn.william.utils.IDUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传服务
 * Created by william on 2017/2/4.
 */
@Service
public class PictureServiceImpl implements PictureService {

    //读取配置文件
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASE_PATH}")
    private String FTP_BASE_PATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;


    @Override
    public Map uploadPicture(MultipartFile uploadFile) {

        Map resultMap = new HashMap<>();

        try {
            String oldName = uploadFile.getOriginalFilename();
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //图片上传
            String imagePath = new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_BASE_PATH, imagePath, newName, uploadFile.getInputStream());
            //返回结果
            if (!result) {
                resultMap.put("error", 1);
                resultMap.put("message", "文件上传失败");
                return resultMap;
            }
            resultMap.put("error", 0);
            resultMap.put("url", IMAGE_BASE_URL + imagePath + "/" + newName);

            return resultMap;
        } catch (Exception e) {
            resultMap.put("error", 1);
            resultMap.put("message", "文件上传发生异常！");
            return resultMap;
        }

    }
}
