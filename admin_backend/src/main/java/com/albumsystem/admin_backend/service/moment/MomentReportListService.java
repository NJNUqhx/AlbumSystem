package com.albumsystem.admin_backend.service.moment;

import com.albumsystem.admin_backend.pojo.Moment;
import com.albumsystem.admin_backend.pojo.MomentReport;

import java.util.List;
import java.util.Map;

public interface MomentReportListService {
    List<MomentReport> momentReportList();

    List<Moment> momentList();

    List<Map<String,String>> momentAndReportList();
}
