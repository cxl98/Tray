package com.easyArch.server.server;

import com.easeArch.common.entry.User;
import com.easeArch.common.enums.StatusCode;
import com.easeArch.common.service.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Server implements API {

    @Autowired
    private UserServer userServer;

    public Object login(User user) {
        User userByUid = userServer.findUserByUsername(user.getUsername());
        if (null!=userByUid&&null!=userByUid.getUid()&&userByUid.getUsername().equals(user.getUsername())){
            return StatusCode.SUCCESS.getCode();
        }

        return StatusCode.ACCOUNT_NOT_MATCH.getCode();
    }

    public Object registry(User user) {
        boolean register = userServer.register(user);
        if (register){
            return StatusCode.SUCCESS.getCode();
        }

        return StatusCode.FAIL.getCode();
    }
}
