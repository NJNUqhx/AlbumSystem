package com.albumsystem.user_backend.service.impl.utils.user.comment;

import com.albumsystem.user_backend.mapper.CommentMapper;
import com.albumsystem.user_backend.mapper.CommentReportMapper;
import com.albumsystem.user_backend.mapper.CommentToAlbumMapper;
import com.albumsystem.user_backend.mapper.CommentToMomentMapper;
import com.albumsystem.user_backend.pojo.*;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.comment.CommentAddService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    @Autowired
    CommentToMomentMapper commentToMomentMapper;
    @Autowired
    CommentReportMapper commentReportMapper;

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
    public Map<String, String> addToMoment(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int momentId = Integer.parseInt(data.get("moment_id"));
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
        CommentToMoment commentToMoment = new CommentToMoment(comment.getCommentId(),momentId);
        commentToMomentMapper.insert(commentToMoment);

        map.put("error_message","success");
        return map;
    }

    @Override
    public Map<String, String> addReport(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int commentId = Integer.parseInt(data.get("comment_id"));
        String reason = data.get("reason");
        Short status = Short.parseShort(data.get("status"));
        Date now = new Date();
        Map<String, String> map = new HashMap<>();
        if(reason == null || reason.length() == 0){
            map.put("error_message","理由不得为空！");
            return map;
        }

        //算被举报人userId
        QueryWrapper<Comment> queryWrapper_m = new QueryWrapper<>();
        queryWrapper_m.eq("comment_id",commentId);
        Comment comment = commentMapper.selectOne(queryWrapper_m);
        int userId = comment.getUserId();

        CommentReport commentReport = new CommentReport(null,commentId, userId,reason,status,now);
        commentReportMapper.insert(commentReport);
        map.put("error_message","success");
        return map;
    }
}
