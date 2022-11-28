package com.albumsystem.user_backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String account;
    private String password;
    private String nickname;

    public void setAccount() {
        this.account = "njnu" + String.format("%05d",this.getUserId());
    }
}
