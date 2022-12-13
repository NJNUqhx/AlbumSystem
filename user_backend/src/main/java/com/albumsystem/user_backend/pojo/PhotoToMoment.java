package com.albumsystem.user_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoToMoment {
    private Integer photoId;
    private Integer momentId;
}
