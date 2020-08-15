package com.easyArch.server.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class Id {
    private static AtomicInteger generator=new AtomicInteger(100);
    private ReentrantLock reentrantLock=new ReentrantLock();


    public int getNextId(){
        try {
            reentrantLock.lock();
            return generator.incrementAndGet();
        } finally {
            reentrantLock.unlock();
        }
    }
}
