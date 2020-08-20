package com.easyArch.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.easyArch.server")
public class TrayApplication{
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(TrayApplication.class);
        context.start();

    }

}
