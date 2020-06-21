package com.easyArch.client.handler;

import com.easeArch.common.handler.Handler;
import com.easeArch.common.util.TrayException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandlerFactory {
    private static final HandlerFactory factory=new HandlerFactory();
    private static Map<String, Handler> map=new ConcurrentHashMap<>();
    private HandlerFactory(){}
    static {
        map.put("login",new LoginHandler());
        map.put("registry",new RegistryHandler());
        map.put("send",new SendHandler());
    }
    public Handler handler(String name){
        if (null!=name){
           return  map.get(name);
        }
        throw new TrayException(">>>业务名不能为空！！！");
    }

    public static HandlerFactory getFactory() {
        return factory;
    }
}
