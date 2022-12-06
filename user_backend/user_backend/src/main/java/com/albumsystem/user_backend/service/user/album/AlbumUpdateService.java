package com.albumsystem.user_backend.service.user.album;

import java.util.Map;

public interface AlbumUpdateService {
    public Map<String,String> update(Map<String,String> data);
    public Map<String,String> star(Map<String,String> data);
    public Map<String,String> name(Map<String,String> data);
    public Map<String,String> authority(Map<String,String> data);
    public Map<String,String> description(Map<String,String> data);
}
