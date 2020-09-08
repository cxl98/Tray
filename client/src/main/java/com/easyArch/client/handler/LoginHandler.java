package com.easyArch.client.handler;

import com.easeArch.common.entry.User;
import com.easeArch.common.handler.Handler;
import com.easeArch.common.service.API;
import com.easyArch.client.netty.TrayClient;

public class LoginHandler implements Handler {


    @Override
    public Object handler(Object object) {
        TrayClient client = TrayClient.getClient();
        API handler = client.handler();
        return handler.login((User) object);
    }

    @Override
    public Object handler(Object object1, Object object2) {
        return null;
    }

}
