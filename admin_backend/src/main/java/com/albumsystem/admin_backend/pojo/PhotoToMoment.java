package com.albumsystem.admin_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoToMoment {
    private Integer photoId;
    private Integer momentId;
}
