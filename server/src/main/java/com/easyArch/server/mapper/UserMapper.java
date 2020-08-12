package com.easyArch.server.mapper;

import com.easeArch.common.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT * FROM tray_user WHERE username=#{username,jdbcType=VARCHAR}")
    User findUserByUsername(String username);
    @Insert("insert into tray_user(username,pwd,nickname,cmt,ip) values (#{username},#{pwd},#{nickname},#{cmt},#{ip})")
    int insertUser(User user);
}
