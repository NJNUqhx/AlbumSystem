package com.albumsystem.user_backend.controller.user.moment;

import com.albumsystem.user_backend.service.user.moment.MomentHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MomentReportController {

    @Autowired
    private MomentHandleService momentHandleService;

    @PostMapping("/user/moment/report/")
    public Map<String, String> report(@RequestParam Map<String, String> data){
        return momentHandleService.report(data);
    }
}
