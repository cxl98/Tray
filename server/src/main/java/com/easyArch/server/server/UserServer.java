package com.easyArch.server.server;

import com.easeArch.common.entry.FriendItemVo;
import com.easeArch.common.entry.User;
import com.easeArch.common.util.TrayException;
import com.easyArch.server.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServer {
    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }

    public boolean register(User user) {
        User userByName = findUserByUsername(user.getUsername());
        if (null!=userByName && userByName.getUsername().equals(user.getUsername())) {
            return true;
        }
        user.setPwd(DigestUtils.md5Hex(user.getPwd()));
        return userMapper.insertUser(user) != 0;
    }
    public List<FriendItemVo> friendByCount(String account){
        System.out.println("account"+account);
        if (null!=account){
            return userMapper.friendByCount(account);
        }
        return null;
    }
}
