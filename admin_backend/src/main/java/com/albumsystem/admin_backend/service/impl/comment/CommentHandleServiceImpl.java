package com.albumsystem.admin_backend.service.impl.comment;

import com.albumsystem.admin_backend.mapper.CommentHandleResultMapper;
import com.albumsystem.admin_backend.mapper.CommentMapper;
import com.albumsystem.admin_backend.mapper.CommentReportMapper;
import com.albumsystem.admin_backend.pojo.*;
import com.albumsystem.admin_backend.service.comment.CommentHandleService;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CommentHandleServiceImpl implements CommentHandleService {
    @Autowired
    private CommentReportMapper commentReportMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentHandleResultMapper commentHandleResultMapper;

    public Map<String, String> commentHandle(Integer reportId, Integer userId, Integer handle, String advice){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        Map<String,String> map = new HashMap<>();
        CommentReport commentReport;
        if(commentReportMapper.selectById(reportId) == null)
        {
            map.put("error_message", "未查询到该评论举报");
            return map;
        }else
        {
            commentReport = commentReportMapper.selectById(reportId);
            if(commentReport.getStatus().equals(handle)){
                map.put("error_message", "处理状态未改变");
                return map;
            }
            commentReport.setStatus(handle);
            commentReportMapper.updateById(commentReport);

            Date time = new Date();
            CommentHandleResult commentHandleResult;
            QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("comment_id",commentReport.getCommentId());
            Comment comment = commentMapper.selectOne(queryWrapper);
            if(handle.equals(1)) {
                // 1 表示 comment 被删除
                comment.setStatus(handle);
                commentHandleResult = new CommentHandleResult(null, commentReport.getCommentId(), userId, "举报成功->删除评论", advice, admin.getAdminId(), time);
                commentMapper.update(comment,queryWrapper);
            }else if(handle.equals(2)){
                // 2 表示 comment 被举报但保留
                comment.setStatus(handle);
                commentHandleResult = new CommentHandleResult(null, commentReport.getCommentId(), userId, "举报失败->保留评论", advice, admin.getAdminId(), time);
                commentMapper.update(comment,queryWrapper);
            }else{
                // 0 表示 comment 还原到未处理的状态
                comment.setStatus(handle);
                commentHandleResult = new CommentHandleResult(null, commentReport.getCommentId(), userId, "撤销处理->默认状态", advice, admin.getAdminId(), time);
                commentMapper.update(comment,queryWrapper);
            }
            commentHandleResultMapper.insert(commentHandleResult);

            map.put("error_message", "success");
        }
        
        
        return map;
    }
}
