package com.uchiha.gearshop.common.extensions;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

public class FileExtension {
    public static String saveFile(String category, MultipartFile file) {
        String host = "http://localhost:8080/";
        if (!file.isEmpty()) {
            byte[] bytes;
            try {
                bytes = file.getBytes();
                String fileName = file.getOriginalFilename();
//				Lấy ngày hiện tại
                long millis = System.currentTimeMillis();
                Date date = new Date(millis);
                String fileLocation = new File("static\\images\\" + category) + "\\" + date.toString() + "_" + fileName;
                FileOutputStream fos = new FileOutputStream(fileLocation);
                fos.write(bytes);
                fos.close();
                return host + "images/" + category + "/" + date.toString() + "_" + fileName;
            } catch (IOException e) {
                System.out.println("SaveFile::Error============" + e.toString());
                return null;
            }
        } else {
            return null;
        }
    }
}
