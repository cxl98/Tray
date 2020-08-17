package com.easyArch.client.netty;

import com.cxl.rpc.remoting.consumer.reference.RpcReferenceBean;
import com.easeArch.common.constants.Constants;
import com.easeArch.common.netty.INettyClient;
import com.easeArch.common.service.API;

public class AbstractTrayClient implements INettyClient {

    private static final RpcReferenceBean rpcReferenceBean = new RpcReferenceBean();


    @Override
    public void init() {
        rpcReferenceBean.init();
    }

    @Override
    public API handler() {
        rpcReferenceBean.setAddress(Constants.ADDRESS);
        rpcReferenceBean.setIface(API.class);
        return (API) rpcReferenceBean.getObject();
    }

    public RpcReferenceBean getRpcReferenceBean() {
        return rpcReferenceBean;
    }
}
