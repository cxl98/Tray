package com.easyArch.client.netty;

import com.cxl.rpc.remoting.consumer.call.CallType;
import com.cxl.rpc.remoting.consumer.call.RpcInvokeCallback;
import com.cxl.rpc.remoting.consumer.reference.RpcReferenceBean;
import com.cxl.rpc.remoting.net.params.RpcResponse;
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
        API object = (API)rpcReferenceBean.getObject();
        return object;
    }

    public RpcReferenceBean getRpcReferenceBean() {
        return rpcReferenceBean;
    }
}
