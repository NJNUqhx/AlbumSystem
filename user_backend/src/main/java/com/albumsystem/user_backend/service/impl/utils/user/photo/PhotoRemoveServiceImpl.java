package com.albumsystem.user_backend.service.impl.utils.user.photo;

import com.albumsystem.user_backend.mapper.PhotoMapper;
import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.photo.PhotoRemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PhotoRemoveServiceImpl implements PhotoRemoveService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int photoId = Integer.parseInt(data.get("photo_id"));
        Photo photo = photoMapper.selectById(photoId);
        Map<String, String> map = new HashMap<>();

        if (!photo.getUserId().equals(user.getUserId())) {
            map.put("error_message","没有权限删除Photo");
            return map;
        }
        if (photo == null) {
            map.put("error_message","用户不存在或已被删除");
            return map;
        }

        photoMapper.deleteById(photoId);
        map.put("error_message","success");
        return map;
    }
}
