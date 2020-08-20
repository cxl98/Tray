package com.easyArch.server.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class Id {
    private static AtomicInteger generator=new AtomicInteger();
    private ReentrantLock reentrantLock=new ReentrantLock();


    public int getNextId(){
            return generator.incrementAndGet();
    }
}
