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
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer commentId;
    private Integer userId;
    private String content;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @Override
    public String toString() {
        return
                "commentId=" + commentId +
                "\n, userId=" + userId +
                "\n, content='" + content + '\'' +
                "\n, status=" + status +
                "\n, time=" + time +
                '\n';
    }
}
