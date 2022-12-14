package com.albumsystem.admin_backend.controller.moment;

import com.albumsystem.admin_backend.pojo.MomentReport;
import com.albumsystem.admin_backend.service.moment.MomentReportListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class MomentReportListController {
    @Autowired
    private MomentReportListService momentReportListService;

    @PostMapping("/admin/moment/report/list/")
    public List<MomentReport> momentReportList(){
        return momentReportListService.momentReportList();
    }

    @PostMapping("/admin/moment/report/all/")
    public List<Map<String,String>> momentAndReportList(){
        return momentReportListService.momentAndReportList();
    }

}
