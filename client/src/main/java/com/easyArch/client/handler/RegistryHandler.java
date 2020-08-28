package com.easyArch.client.handler;

import com.easeArch.common.entry.User;
import com.easeArch.common.handler.Handler;
import com.easeArch.common.service.API;
import com.easyArch.client.netty.TrayClient;

public class RegistryHandler implements Handler {


    public Object handler(Object object) {
        TrayClient client = TrayClient.getClient();
        API handler = client.handler();
        return handler.registry((User) object);
    }
}
