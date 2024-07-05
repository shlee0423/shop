package com.korea.service;

import com.korea.dto.FileDTO;
import com.korea.mapper.ProductMapper;
import jakarta.annotation.Resource;
import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileService {
//    @Resource
//    private FileResource resource;
    @Autowired
    private ProductMapper productMapper;

    private String path = "C:\\Users\\Administrator\\Desktop\\승훈이꺼\\images";
//    public FileDTO get_product_file(String uuid){
//        File file = new File(path, uuid);
//    }
}
