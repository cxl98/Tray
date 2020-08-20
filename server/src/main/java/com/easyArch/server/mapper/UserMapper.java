package com.easyArch.server.mapper;

import com.easeArch.common.entry.FriendItemVo;
import com.easeArch.common.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM tray_user WHERE account=#{username,jdbcType=VARCHAR}")
    User findUserByUsername(String username);
    @Insert("insert into tray_user(username,pwd,nickname,cmt,ip) values (#{username},#{pwd},#{nickname},#{cmt},#{ip})")
    int insertUser(User user);
    @Select("SELECT tray_user.account,tray_user.username,tray_friend.status FROM tray_friend,tray_user where tray_friend.faccount=tray_user.account and tray_friend.account=#{account}")
    List<FriendItemVo> friendByCount(String account);
    @Insert("INSERT into tray_friend(fid,account,faccount,status) values (#{fid},#{account},#{faccount},#{status})")
    int insertFriend(FriendItemVo friendItemVo);
}
