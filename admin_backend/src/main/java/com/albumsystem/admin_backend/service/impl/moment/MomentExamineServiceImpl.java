package com.albumsystem.admin_backend.service.impl.moment;

import com.albumsystem.admin_backend.mapper.MomentExamineResultMapper;
import com.albumsystem.admin_backend.mapper.MomentMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.pojo.Moment;
import com.albumsystem.admin_backend.pojo.MomentExamineResult;
import com.albumsystem.admin_backend.pojo.PhotoExamineResult;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.admin_backend.service.moment.MomentExamineService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MomentExamineServiceImpl implements MomentExamineService {
    @Autowired
    private MomentMapper momentMapper;
    
    @Autowired
    private MomentExamineResultMapper momentExamineResultMapper;
    
    @Override
    public Map<String, String> momentExamine(Map<String, String> map) {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();
        
        Map<String, String> stringMap = new HashMap<>();
        Integer momentId = Integer.valueOf(map.get("momentId"));
        Moment moment;
        QueryWrapper<Moment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("moment_id", momentId);
        if(!momentMapper.exists(queryWrapper)){
            stringMap.put("error_message", "未查找到该动态");
            return stringMap;
        }
        
        moment = momentMapper.selectOne(queryWrapper);
        Integer userId = Integer.valueOf(map.get("userId"));
        Integer examination = Integer.valueOf(map.get("examination"));
        String advice = map.get("advice");
        
        if(moment.getStatus().equals(examination)){
            stringMap.put("error_message", "状态未改变");
            return stringMap;
        }
        
        moment.setStatus(examination);
        momentMapper.updateById(moment);
        Date time = new Date();
        MomentExamineResult momentExamineResult;
        if(examination.equals(1)) {
            momentExamineResult = new MomentExamineResult(null, momentId, userId, "审核通过", advice, admin.getAdminId(), time);
        }else if(examination.equals(2)){
            momentExamineResult = new MomentExamineResult(null, momentId, userId, "审核失败", advice, admin.getAdminId(), time);
        }else{
            momentExamineResult = new MomentExamineResult(null, momentId, userId, "撤销审核", advice, admin.getAdminId(), time);
        }
        momentExamineResultMapper.insert(momentExamineResult);

        stringMap.put("error_message", "success");

        return stringMap;
    }
}
