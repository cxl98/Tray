package com.easeArch.common.netty;

import com.easeArch.common.service.API;

public interface INettyClient {
    /**
     * 初始化连接
     */
    void init();
    /**
     *处理
     */
    API handler();

    /**
     *
     * @return return 状态值
     */
//    int isOn();

    /**
     *
     * @return return 状态值
     */
//    int isOff();

}
