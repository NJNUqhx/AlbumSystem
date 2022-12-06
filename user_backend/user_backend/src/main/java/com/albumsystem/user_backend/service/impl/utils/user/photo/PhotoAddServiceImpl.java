package com.albumsystem.user_backend.service.impl.utils.user.photo;

import com.albumsystem.user_backend.mapper.PhotoMapper;
import com.albumsystem.user_backend.pojo.Photo;
import com.albumsystem.user_backend.pojo.User;
import com.albumsystem.user_backend.service.impl.utils.UserDetailsImpl;
import com.albumsystem.user_backend.service.user.photo.PhotoAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PhotoAddServiceImpl implements PhotoAddService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public Map<String, String> add(Map<String, String> data) {

        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        Map<String, String> map = new HashMap<>();

        String name = data.get("name");
        String address = data.get("address");
        Short authority = Short.parseShort(data.get("authority"));
        Short status = 0;

        if(address == null || address.length()==0){
            map.put("error_message","地址不能为空！");
            return map;
        }
        if(authority!=0&&authority!=1&&authority!=2){
            map.put("error_message","权限设置有误！");
            return map;
        }

        Date now = new Date();
        Photo photo = new Photo(null,user.getUserId(),name,authority,now,status,address);

        photoMapper.insert(photo);

        int length = address.length();
        String endIndex = address.substring(length-4,length);
        System.out.println(endIndex);
        String new_address = "D:\\Github\\AlbumSystem\\images\\" + user.getUserId() + '\\' + photo.getPhotoId() + endIndex;// + ".jpg";
        System.out.println(new_address);

        InputStream is=null;
        OutputStream os=null;
        try {
            //输入流
            is=new FileInputStream(new File(address));
            //输出流
            os=new FileOutputStream(new File(new_address));
            //接下来需要一个字节一个字节的去读，Java中的处理机制会将文件的 的每一个字节转化成一个int型的数字。
            while(true) {
                int i=	is.read();
                //当这个int型数字变为-1的时候，表明读取结束
                if(i==-1) {
                    break;
                }
                //读完以后就开始往里面写了
                os.write(i);
            }
        } catch (FileNotFoundException e) {
            System.out.println("你输入的文件路径是错误的!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("读取异常！");
            e.printStackTrace();
        }
        //使用完IO流之后一定要关闭，可以使用finally类去关闭
        finally {
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(os!=null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        Photo new_photo = new Photo(
                photo.getPhotoId(),
                user.getUserId(),
                photo.getName(),
                photo.getAuthority(),
                photo.getTime(),
                photo.getStatus(),
                new_address
        );
        photoMapper.updateById(new_photo);

        map.put("error_message","success");
        return map;
    }
}
