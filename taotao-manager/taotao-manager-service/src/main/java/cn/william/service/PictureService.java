package cn.william.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by william on 2017/2/4.
 */
public interface PictureService {

    Map uploadPicture(MultipartFile uploadFile);

}
