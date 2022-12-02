package com.albumsystem.admin_backend.service.impl.photo;

import com.albumsystem.admin_backend.mapper.PhotoExamineResultMappper;
import com.albumsystem.admin_backend.mapper.PhotoMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.pojo.Photo;
import com.albumsystem.admin_backend.pojo.PhotoExamineResult;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.admin_backend.service.photo.PhotoExamineService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class PhotoExamineServiceImpl implements PhotoExamineService {
    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private PhotoExamineResultMappper photoExamineResultMappper;
    @Override
    public Map<String, String> photoExamine(Integer photoId, Integer userId, Integer examination, String advice) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        Map<String,String> map = new HashMap<>();
        Photo photo;
        if(photoMapper.selectById(photoId) == null)
        {
            map.put("error_message", "未查询到该照片");
        }else
        {
            photo = photoMapper.selectById(photoId);
            if(photo.getStatus().equals(examination)){
                map.put("error_message", "审核状态未改变");
            }
            photo.setStatus(examination);
            photoMapper.updateById(photo);

            Date time = new Date();
            PhotoExamineResult photoExamineResult;
            if(examination.equals(1)) {
                photoExamineResult = new PhotoExamineResult(null, photoId, userId, "审核通过", advice, admin.getAdminId(), time);
            }else{
                photoExamineResult = new PhotoExamineResult(null, photoId, userId, "审核失败", advice, admin.getAdminId(), time);
            }
            photoExamineResultMappper.insert(photoExamineResult);

            map.put("error_message", "success");
        }

        return map;
    }
}
