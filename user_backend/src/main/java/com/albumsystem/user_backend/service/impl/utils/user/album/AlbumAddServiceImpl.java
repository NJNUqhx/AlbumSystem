//package com.albumsystem.user_backend.service.impl.utils.user.album;
//
//import com.albumsystem.user_backend.mapper.AlbumMapper;
//import com.albumsystem.user_backend.pojo.Album;
//import com.albumsystem.user_backend.pojo.User;
//import com.albumsystem.user_backend.service.user.album.AlbumAddService;
//import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class AlbumAddServiceImpl implements AlbumAddService {
//
//    @Autowired
//    private AlbumMapper albumMapper;
//
//    @Override
//    public Map<String, String> add(Map<String, String> data) {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
//        User user = loginUser.getUser();
//
//        Map<String, String> map = new HashMap<>();
//
//        int userId = Integer.parseInt(data.get("user_id"));
//        Short authority = Short.parseShort(data.get("authority"));
//        String name = data.get("name");
//        String description = data.get("description");
//
//        if (userId != user.getUserId()){
//            map.put("error_message","无权限访问");
//            return map;
//        }
//        /*
//        判断是否正确......(代码)
//         */
//
//        Date now = new Date();
//        Album album = new Album(null,userId,authority,name,0,now,description);
//        albumMapper.insert(album);
//        return map;
//    }
//}
