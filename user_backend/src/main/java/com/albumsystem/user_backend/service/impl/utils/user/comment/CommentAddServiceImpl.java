package com.albumsystem.user_backend.service.impl.utils.user.comment;

import com.albumsystem.user_backend.mapper.CommentMapper;
import com.albumsystem.user_backend.mapper.CommentToAlbumMapper;
import com.albumsystem.user_backend.pojo.Comment;
import com.albumsystem.user_backend.pojo.CommentToAlbum;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.comment.CommentAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommentAddServiceImpl implements CommentAddService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentToAlbumMapper commentToAlbumMapper;

    @Override
    public Map<String, String> addToAlbum(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int albumId = Integer.parseInt(data.get("album_id"));
        String content = data.get("content");
        Date now = new Date();
        int status = 0;

        Map<String, String> map = new HashMap<>();
        if( content.length() == 0){
            map.put("error_message","评论内容为空！");
            return map;
        }
        else if ( content.length() > 128){
            map.put("error_message","评论过长！");
            return map;
        }

        Comment comment = new Comment(null,user.getUserId(),content,now,status);
        commentMapper.insert(comment);
        CommentToAlbum commentToAlbum = new CommentToAlbum(comment.getCommentId(),albumId);
        commentToAlbumMapper.insert(commentToAlbum);

        map.put("error_message","success");
        return map;
    }

    @Override
    public Map<String, String> addMoment(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();

        return map;
    }

    @Override
    public Map<String, String> addReport(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();

        return map;
    }
}
