package com.albumsystem.user_backend.service.impl.utils.user.album;

import com.albumsystem.user_backend.mapper.AlbumMapper;
import com.albumsystem.user_backend.pojo.Album;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.user.album.AlbumAddService;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AlbumAddServiceImpl implements AlbumAddService {

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();

        Short authority = Short.parseShort(data.get("authority"));
        String name = data.get("name");
        String description = data.get("description");

        if(authority!=0&&authority!=1&&authority!=2){
            map.put("error_message","权限设置有误！");
            return map;
        }
        if(description.length() == 0) description = "这个人很懒，什么也没留下......";

        Date now = new Date();
        Album album = new Album(null,user.getUserId(),authority,name,0,now,description);
        albumMapper.insert(album);
        map.put("error_message","success");
        return map;
    }
}
