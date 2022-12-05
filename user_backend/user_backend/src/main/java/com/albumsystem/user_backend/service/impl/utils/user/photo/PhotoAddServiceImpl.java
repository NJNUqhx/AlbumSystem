package com.albumsystem.user_backend.service.impl.utils.user.photo;

import com.albumsystem.user_backend.mapper.PhotoMapper;
import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.photo.PhotoAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PhotoAddServiceImpl implements PhotoAddService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();

        String name = data.get("name");
        String address = data.get("address");
        Short authority = Short.parseShort(data.get("authority"));
        Short status = 0;

        if(address == null || address.length()==0){
            map.put("error_message","地址不能为空！");
            return map;
        }
        if(authority!=0&&authority!=1&&authority!=2){
            map.put("error_message","权限设置有误！");
            return map;
        }
        /*
        判断是否正确......(代码)
         */

        Date now = new Date();
        Photo photo = new Photo(null,user.getUserId(),name,authority,now,status,address);
        photoMapper.insert(photo);
        map.put("error_message","success");
        return map;
    }
}
