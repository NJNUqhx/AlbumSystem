package com.albumsystem.admin_backend.mapper;

import com.albumsystem.admin_backend.pojo.PhotoToMoment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PhotoToMomentMapper extends BaseMapper<PhotoToMoment> {
}
