package com.albumsystem.admin_backend.controller.moment;

import com.albumsystem.admin_backend.service.moment.MomentReportHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MomentHandleController {
    @Autowired
    private MomentReportHandleService momentReportHandleService;

    @PostMapping("/admin/moment/handle/")
    public Map<String, String> momentReportHandle(@RequestParam Map<String,String> map){
        return momentReportHandleService.momentReportHandle(map);
    }
}
