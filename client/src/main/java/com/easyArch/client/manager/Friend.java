package com.easyArch.client.manager;

import com.cxl.rpc.util.ProxyPush;
import com.cxl.rpc.util.Push;
import com.easeArch.common.entry.User;

public class Friend implements Push {
    @Override
    public void exec(Object o) {
        Object response = ProxyPush.getInstance().getResponse();
        if (null!=response&&!"".equals(response)){
              User user= (User) response;
            FriendManager.getInstance().onFriendLogin(user.getAccount());
        }
    }
}
