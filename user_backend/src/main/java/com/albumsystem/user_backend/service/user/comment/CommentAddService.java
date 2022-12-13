package com.albumsystem.user_backend.service.user.comment;

import java.util.Map;

public interface CommentAddService {
    public Map<String,String> addToAlbum(Map<String,String> data);
    public Map<String,String> addToMoment(Map<String,String> data);
    public Map<String,String> addReport(Map<String,String> data);
}
