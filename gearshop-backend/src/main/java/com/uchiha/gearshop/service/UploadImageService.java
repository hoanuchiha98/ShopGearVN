package com.uchiha.gearshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImageService {
    String uploadImage(String category, MultipartFile file);
}
