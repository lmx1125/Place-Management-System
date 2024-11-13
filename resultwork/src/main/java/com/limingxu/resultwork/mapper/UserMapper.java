package com.limingxu.resultwork.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.limingxu.resultwork.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM t_user WHERE user_name = #{userName}")  // 使用正确的列名
    User findByUsername(String userName);
}