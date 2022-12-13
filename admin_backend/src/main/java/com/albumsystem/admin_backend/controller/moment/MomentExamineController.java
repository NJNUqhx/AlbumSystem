package com.albumsystem.admin_backend.controller.moment;

import com.albumsystem.admin_backend.service.moment.MomentExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MomentExamineController {
    @Autowired
    private MomentExamineService momentExamineService;

    @PostMapping("/admin/moment/examine/")
    public Map<String, String> momentExamine(@RequestParam Map<String, String> map){
        return momentExamineService.momentExamine(map);
    }
}
