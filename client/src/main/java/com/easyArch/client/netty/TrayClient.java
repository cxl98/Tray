package com.easyArch.client.netty;

import com.cxl.rpc.remoting.consumer.reference.RpcReferenceBean;
import com.easeArch.common.netty.INettyClient;
import com.easeArch.common.service.API;

public class TrayClient implements INettyClient {
    private String address;
    private RpcReferenceBean rpcReferenceBean=new RpcReferenceBean();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void connect() {
        rpcReferenceBean.init();
    }

    @Override
    public API handler() {
        rpcReferenceBean.setAddress(address);
        rpcReferenceBean.setIface(API.class);
        API object = (API) rpcReferenceBean.getObject();
        return object;
    }



}
