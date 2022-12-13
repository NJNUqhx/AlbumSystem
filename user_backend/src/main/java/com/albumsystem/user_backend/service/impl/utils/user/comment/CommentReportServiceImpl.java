package com.albumsystem.user_backend.service.impl.utils.user.comment;

import com.albumsystem.user_backend.mapper.CommentMapper;
import com.albumsystem.user_backend.mapper.CommentReportMapper;
import com.albumsystem.user_backend.pojo.*;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.comment.CommentReportService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommentReportServiceImpl implements CommentReportService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentReportMapper commentReportMapper;

    @Override
    public Map<String, String> report(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        System.out.println(user.getUserId());

        int commentId = Integer.parseInt(data.get("comment_id"));
        String reason = data.get("reason");
        Short status = 0;
        Date now = new Date();

        Map<String, String> map = new HashMap<>();
        if(reason == null || reason.length() == 0){
            map.put("error_message","理由不得为空！");
            return map;
        }

        //算被举报人userId
        QueryWrapper<Comment> queryWrapper_c = new QueryWrapper<>();
        queryWrapper_c.eq("comment_id",commentId);
        Comment comment = commentMapper.selectOne(queryWrapper_c);
        int userId = comment.getUserId();

        CommentReport commentReport = new CommentReport(null,commentId, userId,reason,status,now);
        commentReportMapper.insert(commentReport);

        map.put("error_message","success");
        return map;
    }
}
