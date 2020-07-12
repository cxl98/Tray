package com.easyArch.server;

import com.cxl.rpc.remoting.provider.RpcProviderFactory;
import com.easeArch.common.service.API;
import com.easyArch.server.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

@ComponentScan(basePackages = "com.easyArch.server")
public class TrayApplication{
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TrayApplication.class);
        Server bean = context.getBean(Server.class);
        context.start();
        RpcProviderFactory factory=new RpcProviderFactory();
        factory.addService(API.class.getName(),null,bean);

        factory.start();
        while(!Thread.currentThread().isInterrupted()){
            TimeUnit.HOURS.sleep(1);
        }
        factory.stop();
    }

}
