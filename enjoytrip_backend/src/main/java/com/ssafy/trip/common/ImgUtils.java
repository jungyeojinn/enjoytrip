package com.ssafy.trip.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImgUtils {

    public String saveImage(MultipartFile img, String path) {
        String uuid = UUID.randomUUID().toString();    //파일 이름 중복 방지
        String savedFilename = uuid;
        String fileName=img.getOriginalFilename();
        int extensionIdx = fileName.lastIndexOf(".");
        String extension=fileName.substring(extensionIdx + 1);

        savedFilename = savedFilename+'.'+extension;
        String savedPath = "C:/upload/" + path +'/' + savedFilename;

        File file = new File(savedPath);

        try {
            img.transferTo(file);
            return savedFilename;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteImage(String imgPath, String path) {
        File fileToDelete = new File("C:/upload/" + path +'/'+ imgPath);
        boolean success = fileToDelete.delete();
    }
}
