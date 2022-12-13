package com.albumsystem.admin_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentToMoment {
    private Integer commentId;
    private Integer MomentId;
}
