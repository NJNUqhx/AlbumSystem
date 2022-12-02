package com.albumsystem.admin_backend.service.impl.comment;

import com.albumsystem.admin_backend.mapper.CommentHandleResultMapper;
import com.albumsystem.admin_backend.mapper.CommentMapper;
import com.albumsystem.admin_backend.mapper.CommentReportMapper;
import com.albumsystem.admin_backend.pojo.*;
import com.albumsystem.admin_backend.service.comment.CommentHandleService;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
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
        }else
        {
            commentReport = commentReportMapper.selectById(reportId);
            if(commentReport.getStatus().equals(handle)){
                map.put("error_message", "处理状态未改变");
            }
            commentReport.setStatus(handle);
            commentReportMapper.updateById(commentReport);

            Date time = new Date();
            CommentHandleResult commentHandleResult;
            Comment comment = commentMapper.selectById(commentReport.getCommentId());
            if(handle.equals(1)) {
                comment.setStatus(1);
                commentHandleResult = new CommentHandleResult(null, commentReport.getCommentId(), userId, "删除评论", advice, admin.getAdminId(), time);
                commentMapper.updateById(comment);
            }else if(handle.equals(2)){
                comment.setStatus(0);
                commentHandleResult = new CommentHandleResult(null, commentReport.getCommentId(), userId, "保留评论", advice, admin.getAdminId(), time);
                commentMapper.updateById(comment);
            }else{
                comment.setStatus(0);
                commentHandleResult = new CommentHandleResult(null, commentReport.getCommentId(), userId, "撤销处理", advice, admin.getAdminId(), time);
            }
            commentHandleResultMapper.insert(commentHandleResult);

            map.put("error_message", "success");
        }
        
        
        return map;
    }
}
