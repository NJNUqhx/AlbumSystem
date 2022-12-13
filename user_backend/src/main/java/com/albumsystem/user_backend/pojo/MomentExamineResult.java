package com.albumsystem.user_backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MomentExamineResult {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer momentId;
    private Integer userId;
    private String result;
    private String advice;
    private Integer adminId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
}
