package com.albumsystem.admin_backend.service.impl.account;

import com.albumsystem.admin_backend.pojo.Admin;
import com.albumsystem.admin_backend.service.account.InfoService;
import com.albumsystem.admin_backend.service.impl.utils.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class InfoServiceImpl implements InfoService {
    @Override
    public Map<String, String> getinfo() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginAdmin = (UserDetailsImpl) authentication.getPrincipal();
        Admin admin = loginAdmin.getAdmin();
        Map<String,String> map = new HashMap<>();
        map.put("error_message", "success");
        map.put("adminId", admin.getAdminId().toString());
        map.put("account", admin.getAccount());

        return map;
    }
}
