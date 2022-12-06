package com.albumsystem.user_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentToAlbum {
    private Integer commentId;
    private Integer albumId;
}
