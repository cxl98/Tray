package com.easyArch.server.server;

import com.easeArch.common.entry.FriendItemVo;
import com.easeArch.common.entry.User;
import com.easeArch.common.enums.StatusCode;
import com.easeArch.common.service.API;
import com.easyArch.server.util.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Server implements API {

    @Autowired
    private UserServer userServer;
    @Autowired
    private Id id;


    public Object login(User user) {
        User userByUid = userServer.findUserByUsername(user.getAccount());
        if (null!=userByUid&&userByUid.getAccount().equals(user.getAccount())){
            return userByUid;
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

    @Override
    public List<FriendItemVo> friend(String account) {
        List<FriendItemVo> friendItemVos = userServer.friendByCount(account);
        System.out.println(friendItemVos);
        if (null==friendItemVos){
            return null;
        }
        return friendItemVos;
    }
}
