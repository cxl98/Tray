package com.easyArch.client.handler;

import com.easeArch.common.entry.User;
import com.easeArch.common.handler.Handler;
import com.easeArch.common.service.API;
import com.easyArch.client.netty.TrayClient;

public class LoginHandler implements Handler {


    @Override
    public Object handler(Object object) {
        TrayClient client=new TrayClient();
        API handler = client.handler();
        return handler.login((User) object);
    }
}
