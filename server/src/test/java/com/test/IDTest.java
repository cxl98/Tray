package com.test;

import com.easyArch.server.util.Id;
import com.sun.xml.internal.bind.v2.model.core.ID;

public class IDTest {
    public static void main(String[] args) {
        Id id=new Id();
        for (int i = 0; i < 100000; i++) {
            new Thread(()->System.out.println("当前id"+id.getNextId())).start();

        }
    }
}
