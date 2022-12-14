package com.albumsystem.admin_backend.service.impl.moment;

import com.albumsystem.admin_backend.mapper.MomentMapper;
import com.albumsystem.admin_backend.mapper.MomentReportMapper;
import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.pojo.Moment;
import com.albumsystem.admin_backend.pojo.MomentReport;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.admin_backend.service.moment.MomentReportListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MomentReportListServiceImpl implements MomentReportListService {
    @Autowired
    private MomentReportMapper momentReportMapper;

    @Autowired
    private MomentMapper momentMapper;

    @Override
    public List<MomentReport> momentReportList() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();

        QueryWrapper<MomentReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("status").orderByDesc("time");
        return momentReportMapper.selectList(queryWrapper);
    }

    @Override
    public List<Moment> momentList() {
        List<MomentReport> momentReportList = this.momentReportList();
        if(momentReportList.isEmpty()) return null;
        List<Integer> momentIdList = new ArrayList<>();
        for(MomentReport momentReport:momentReportList){
            momentIdList.add(momentReport.getMomentId());
        }
        List<Moment> moments = new ArrayList<>();
        QueryWrapper<Moment> queryWrapper = new QueryWrapper<>();

        for(Integer id:momentIdList){
            queryWrapper.eq("moment_id",id);
            Moment moment = momentMapper.selectOne(queryWrapper);
            moments.add(moment);
            queryWrapper.clear();
        }

        return moments;
    }

    @Override
    public List<Map<String, String>> momentAndReportList() {
        List<Map<String,String>> list = new ArrayList<>();
        List<MomentReport> momentReports = this.momentReportList();
        if(momentReports.isEmpty()) return null;
        List<Moment> moments = this.momentList();
        for(int i = 0; i < momentReports.size(); ++i){
            Map<String,String> map = new HashMap<>();
            map.put("reportId",momentReports.get(i).getReportId().toString());
            map.put("momentId",momentReports.get(i).getMomentId().toString());
            map.put("userId",momentReports.get(i).getUserId().toString());
            map.put("reason",momentReports.get(i).getReason());
            map.put("reportStatus",momentReports.get(i).getStatus().toString());
            map.put("reportTime",momentReports.get(i).getTime().toString());

            map.put("authority", moments.get(i).getAuthority().toString());
            map.put("description", moments.get(i).getDescription());
            map.put("star", moments.get(i).getStar().toString());
            map.put("momentTime", moments.get(i).getTime().toString());

            list.add(map);
        }
        return list;
    }
}
