package com.albumsystem.user_backend.service.impl.utils.user.comment;

import com.albumsystem.user_backend.mapper.CommentMapper;
import com.albumsystem.user_backend.mapper.CommentToAlbumMapper;
import com.albumsystem.user_backend.mapper.CommentToMomentMapper;
import com.albumsystem.user_backend.mapper.UserMapper;
import com.albumsystem.user_backend.pojo.*;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.comment.CommentGetListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommentGetListServiceImpl implements CommentGetListService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentToAlbumMapper commentToAlbumMapper;
    @Autowired
    private CommentToMomentMapper commentToMomentMapper;

    @Override
    public List<Comment> commentGetListAlbum(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int albumId = Integer.parseInt(data.get("album_id"));
        QueryWrapper<CommentToAlbum> commentToAlbumQueryWrapper = new QueryWrapper<>();
        commentToAlbumQueryWrapper.eq("album_id",albumId);
        List<CommentToAlbum> commentToAlbumList = commentToAlbumMapper.selectList(commentToAlbumQueryWrapper);
        List<Comment> ans = new ArrayList<>();
        for (CommentToAlbum commentToAlbum:commentToAlbumList){
            int commentId = commentToAlbum.getCommentId();
            Comment comment = commentMapper.selectById(commentId);
            if (comment.getStatus() == 1){//????????????????????????
                ans.add(comment);
            }
        }
        return ans;
    }

    @Override
    public List<Comment> commentGetListMoment(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int momentId = Integer.parseInt(data.get("moment_id"));
        QueryWrapper<CommentToMoment> commentToMomentQueryWrapper = new QueryWrapper<>();
        commentToMomentQueryWrapper.eq("moment_id",momentId);
        List<CommentToMoment> commentToMomentList = commentToMomentMapper.selectList(commentToMomentQueryWrapper);
        List<Comment> ans = new ArrayList<>();
        for (CommentToMoment commentToMoment:commentToMomentList){
            int commentId = commentToMoment.getCommentId();
            Comment comment = commentMapper.selectById(commentId);
            if (comment.getStatus() == 1){//????????????????????????
                ans.add(comment);
            }
        }
        return ans;
    }

    @Override
    public String getUsername(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();

        int commentId = Integer.parseInt(data.get("comment_id"));
        QueryWrapper<Comment> commentQueryWrapper = new QueryWrapper<>();
        commentQueryWrapper.eq("comment_id",commentId);
        Comment comment = commentMapper.selectOne(commentQueryWrapper);

        int userId = comment.getUserId();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id",userId);
        User user = userMapper.selectOne(userQueryWrapper);

        return user.getNickname();
    }
}
