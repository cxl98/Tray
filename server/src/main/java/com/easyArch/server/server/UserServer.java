package com.easyArch.server.server;

import com.easeArch.common.entry.User;
import com.easyArch.server.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServer {
    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }

    public boolean register(User user) {
        User userByName = findUserByUsername(user.getUsername());
        if (null!=userByName && null!= userByName.getUid()  && userByName.getUsername().equals(user.getUsername())) {
            return true;
        }
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        return userMapper.insertUser(user) != 0;
    }
}
