package com.easyArch.client.handler;

import com.easeArch.common.entry.User;
import com.easeArch.common.handler.Handler;
import com.easeArch.common.service.API;
import com.easyArch.client.netty.TrayClient;

public class InsertHandler implements Handler {
    @Override
    public Object handler(Object object) {
        return null;
    }


    @Override
    public Object handler(Object object1, Object object2) {
        TrayClient client = TrayClient.getClient();
        API handler = client.handler();
        return handler.insertFriend((String)object1,(String)object2);
    }
}
