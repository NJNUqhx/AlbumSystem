package com.albumsystem.admin_backend.service.impl.moment;

import com.albumsystem.admin_backend.mapper.MomentHandleResultMapper;
import com.albumsystem.admin_backend.mapper.MomentMapper;
import com.albumsystem.admin_backend.mapper.MomentReportMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.pojo.Moment;
import com.albumsystem.admin_backend.pojo.MomentHandleResult;
import com.albumsystem.admin_backend.pojo.MomentReport;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.admin_backend.service.moment.MomentReportHandleService;
import com.albumsystem.admin_backend.service.moment.MomentReportListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MomentReportHandleServiceImpl implements MomentReportHandleService {
    @Autowired
    private MomentMapper momentMapper;
    @Autowired
    private MomentHandleResultMapper momentHandleResultMapper;
    @Autowired
    private MomentReportMapper momentReportMapper;

    @Override
    public Map<String, String> momentReportHandle(Map<String, String> map) {

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        Map<String, String> stringMap = new HashMap<>();

        Integer reportId = Integer.valueOf(map.get("reportId"));
        Integer userId = Integer.valueOf(map.get("userId"));
        Integer momentId = Integer.valueOf(map.get("momentId"));
        Integer handle = Integer.valueOf(map.get("handle"));
        String advice = map.get("advice");

        QueryWrapper<MomentReport> momentReportQueryWrapper = new QueryWrapper<>();
        if(!momentReportMapper.exists(momentReportQueryWrapper)){
            stringMap.put("error_message","未查询到该动态举报");
            return stringMap;
        }

        MomentReport momentReport = momentReportMapper.selectOne(momentReportQueryWrapper);
        if(momentReport.getStatus().equals(handle)){
            stringMap.put("error_message","处理状态未改变");
            return stringMap;
        }

        QueryWrapper<Moment> momentQueryWrapper = new QueryWrapper<>();
        momentQueryWrapper.eq("moment_id",momentId);
        if(!momentMapper.exists(momentQueryWrapper)){
            stringMap.put("error_message","未查询到该动态");
            return stringMap;
        }
        Moment moment = momentMapper.selectOne(momentQueryWrapper);

        momentReport.setStatus(handle);
        momentReportMapper.updateById(momentReport);
        MomentHandleResult momentHandleResult;

        Date time = new Date();
        String result;
        if(handle.equals(1)){
            // 举报成功->删除动态
            moment.setStatus(2);
            result = "举报成功->删除动态";
            momentHandleResult = new MomentHandleResult(null,momentId,userId,result,advice,admin.getAdminId(),time);
        } else if (handle.equals(2)) {
            // 举报失败->保留动态
            moment.setStatus(1);
            result = "举报失败->保留动态";
            momentHandleResult = new MomentHandleResult(null,momentId,userId,result,advice,admin.getAdminId(),time);

        }else{
            result = "撤销处理";
            momentHandleResult = new MomentHandleResult(null,momentId,userId,result,advice,admin.getAdminId(),time);
        }
        momentHandleResultMapper.insert(momentHandleResult);

        stringMap.put("error_message", "success");
        return stringMap;
    }
}
