package com.albumsystem.admin_backend.service.photo;

import java.util.Map;

public interface PhotoExamineService {
    Map<String, String> photoExamine(Integer photoId, Integer userId, Integer examination, String advice);
}
