package com.albumsystem.admin_backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Moment {
    @TableId(type = IdType.AUTO)
    private Integer momentId;
    private Integer userId;
    private Integer authority;
    private String description;
    private Integer star;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date time;
    private Integer status;
}
