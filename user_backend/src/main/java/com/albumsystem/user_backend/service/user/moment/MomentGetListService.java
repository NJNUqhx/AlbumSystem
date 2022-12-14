package com.albumsystem.user_backend.service.user.moment;

import com.albumsystem.user_backend.pojo.*;

import java.util.List;
import java.util.Map;

public interface MomentGetListService {
    List<Moment> getList();
    List<Comment> getCommentList(Map<String, String> data);
    public List<Moment> getUsersList(Map<String, String> data);

    List<MomentExamineResult> getMomentExamineResultList();
    List<MomentHandleResult> getMomentHandleResultList();
}
