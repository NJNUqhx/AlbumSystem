package com.albumsystem.admin_backend.service.impl.comment;

import com.albumsystem.admin_backend.mapper.CommentMapper;
import com.albumsystem.admin_backend.mapper.CommentReportMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.pojo.Comment;
import com.albumsystem.admin_backend.pojo.CommentReport;
import com.albumsystem.admin_backend.service.comment.CommentListService;
import com.albumsystem.admin_backend.service.comment.CommentReportListService;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentReportListServiceImpl implements CommentReportListService{
    @Autowired
    private CommentReportMapper commentReportMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Integer> commentReportListId() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        QueryWrapper<CommentReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("status").orderByDesc("time");

        List<CommentReport> list = commentReportMapper.selectList(queryWrapper);
//        System.out.println(list);
        List<Integer> id = new ArrayList<>();
        for(CommentReport commentReport:list){
            id.add(commentReport.getReportId());
        }

        return id;
    }

    @Override
    public List<Comment> commentContentList(List<Integer> idList) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        List<Comment> list = new ArrayList<>();
        QueryWrapper<CommentReport> queryWrapper = new QueryWrapper<>();
        for(Integer id:idList){
            queryWrapper.clear();
            queryWrapper.eq("report_id", id);
            CommentReport commentReport = commentReportMapper.selectOne(queryWrapper);
            Comment comment = commentMapper.selectById(commentReport.getCommentId());
            if(comment == null)
            {
                return list;
            }
            list.add(comment);
        }

        return list;
    }

    @Override
    public List<CommentReport> commentReportList(List<Integer> id) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();
        return commentReportMapper.selectBatchIds(id);
    }
}
