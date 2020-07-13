package com.easyArch.server.mapper;

import com.easeArch.common.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT * FROM tray_user WHERE username=#{username,jdbcType=VARCHAR}")
    User findUserByUsername(String username);
    @Insert("insert into tray_user(uid,username,password,createMillisTime,ip) values (#{uid},#{username},#{password},#{createMillisTime},#{ip})")
    int insertUser(User user);
}
