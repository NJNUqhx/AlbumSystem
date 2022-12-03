package com.albumsystem.user_backend.service.impl.utils.user.photo;

import com.albumsystem.user_backend.mapper.PhotoMapper;
import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.photo.PhotoUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PhotoUpdateServiceImpl implements PhotoUpdateService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int photoId = Integer.parseInt(data.get("photo_id"));
        Short authority = Short.parseShort(data.get("authority"));
        String name = data.get("name");
        String address = data.get("address");
        /*
        判断是否正确......(代码)
         */
        Map<String, String> map = new HashMap<>();
        Photo photo = photoMapper.selectById(photoId);

        if (!photo.getUserId().equals(user.getUserId())) {
            map.put("error_message","没有权限修改该Photo");
            return map;
        }
        if (photo == null) {
            map.put("error_message","用户不存在或已被删除");
            return map;
        }
        if(address == null || address.length()==0){
            map.put("error_message","地址不能为空！");
            return map;
        }
        if(authority!=0&&authority!=1&&authority!=2){
            map.put("error_message","权限设置有误！");
            return map;
        }

        Photo new_photo = new Photo(
                photo.getPhotoId(),
                user.getUserId(),
                name,
                authority,
                new Date(),
                null,
                address
        );

        photoMapper.updateById(new_photo);
        map.put("error_message","success");

        return map;
    }
}
