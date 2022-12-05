package com.albumsystem.admin_backend.controller.photo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotoUploadController {
    SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");

    @PostMapping("/admin/photo/upload/")
    public Map<String,Object> fileUpload(MultipartFile file, HttpServletRequest req) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String originName = file.getOriginalFilename();
        if(!originName.endsWith(".jpg")){
            map.put("error_message", "文件类型不符合");
            return map;
        };
        String realPath = "D://GitHub//AlbumSystem//images//target";
        File folder = new File(realPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String newName = "test.jpg";
        file.transferTo(new File(folder, newName));
        map.put("error_message", "success");
        return map;
    }
}
