package com.albumsystem.admin_backend;

import com.albumsystem.admin_backend.mapper.CommentMapper;
import com.albumsystem.admin_backend.pojo.Comment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MyBatisPlusPluginsTest {
    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void Test(){
        Page<Comment> page = new Page<>(2,3);
        commentMapper.selectPage(page, null);
        List<Comment> list = page.getRecords();
        for(Comment comment:list){
            System.out.print(comment);
        }
        System.out.println(page.getPages());
        System.out.println(page.getCurrent());
    }
}
