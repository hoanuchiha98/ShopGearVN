package com.uchiha.gearshop.service.impl;

import com.uchiha.gearshop.common.extensions.FileExtension;
import com.uchiha.gearshop.service.UploadImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadImageImplService implements UploadImageService {
    @Override
    public String uploadImage(String category, MultipartFile file) {
        String pathFile = "";
        try {
            pathFile = FileExtension.saveFile(category, file);
        } catch (Exception e) {
            System.out.println("Image::Erorr====================" + e.toString());
        }
        return pathFile;
    }
}
