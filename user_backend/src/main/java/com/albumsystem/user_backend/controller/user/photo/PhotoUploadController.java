package com.albumsystem.user_backend.controller.user.photo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PhotoUploadController {

    @PostMapping("/user/photo/upload/backend/")
    public Map<String,Object> fileUpload(MultipartFile file, HttpServletRequest req) throws IOException, IOException {
        Map<String, Object> map = new HashMap<>();
        String originName = file.getOriginalFilename();
        if(!originName.endsWith(".jpg")){
            map.put("error_message", "文件类型不符合");
            return map;
        };
        String realPath = "D://GitHub//AlbumSystem//images//temp";
        File folder = new File(realPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String newName = "temp.jpg";
        file.transferTo(new File(folder, newName));
        map.put("error_message", "success");
        return map;
    }
}
