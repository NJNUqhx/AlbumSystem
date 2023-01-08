package com.albumsystem.user_backend.service.impl.utils.user.moment;

import com.albumsystem.user_backend.mapper.MomentMapper;
import com.albumsystem.user_backend.mapper.MomentReportMapper;
import com.albumsystem.user_backend.pojo.Moment;
import com.albumsystem.user_backend.pojo.MomentReport;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.moment.MomentHandleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MomentHandleServiceImpl implements MomentHandleService {

    @Autowired
    private MomentMapper momentMapper;
    @Autowired
    private MomentReportMapper momentReportMapper;

    @Override
    public Map<String, String> report(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int momentId = Integer.parseInt(data.get("moment_id"));
        String reason = data.get("reason");
        Short status = 0;
        Date now = new Date();

        Map<String, String> map = new HashMap<>();
        if(reason == null || reason.length() == 0){
            map.put("error_message","理由不得为空！");
            return map;
        }

        //算被举报人userId
        QueryWrapper<Moment> queryWrapper_m = new QueryWrapper<>();
        queryWrapper_m.eq("moment_id",momentId);
        Moment moment = momentMapper.selectOne(queryWrapper_m);
        int userId = user.getUserId();

        MomentReport momentReport = new MomentReport(null,momentId, userId,reason,status,now);
        momentReportMapper.insert(momentReport);
        map.put("error_message","success");
        return map;
    }

    @Override
    public Map<String, String> star(Map<String,String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();
        int momentId = Integer.parseInt(data.get("moment_id"));
        Moment moment = momentMapper.selectById(momentId);
        int userId = user.getUserId();
        Short authority = moment.getAuthority();
        String description = moment.getDescription();
        int star = moment.getStar()+1;
        Date time = moment.getTime();
        int status = moment.getStatus();

        Moment new_moment = new Moment(
                momentId,userId,authority,description,star,time,status, moment.getPhotoId()
        );
        momentMapper.updateById(new_moment);
        map.put("error_message","success");
        return map;
    }
}
