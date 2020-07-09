package com.easeArch.common.netty;

import com.easeArch.common.service.API;

public interface INettyClient {
    /**
     * 连接服务器
     */
    void connect() throws Exception;

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
