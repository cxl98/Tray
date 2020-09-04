package com.easeArch.common.handler;

public interface Handler {
    /**
     *
     * @param object 业务api
     */
    Object handler(Object object);

    Object handler(Object object1,Object object2);
}
