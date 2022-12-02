package com.albumsystem.admin_backend.service.comment;

import java.util.Map;

public interface CommentHandleService {
    Map<String, String> commentHandle(Integer commentId, Integer userId, Integer handle, String advice);
}
