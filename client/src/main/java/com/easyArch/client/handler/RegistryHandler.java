package com.easyArch.client.handler;

import com.easeArch.common.entry.User;
import com.easeArch.common.handler.Handler;
import com.easeArch.common.service.API;
import com.easyArch.client.netty.TrayClient;

public class RegistryHandler implements Handler {
    @Override
    public Object handler(Object object) {
        TrayClient client=new TrayClient();
        client.setAddress("127.0.0.1:8888");
        client.connect();
        API handler = client.handler();
        Object login = handler.registry((User) object);
        return login;
    }
}
