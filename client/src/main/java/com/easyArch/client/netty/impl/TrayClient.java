package com.easyArch.client.netty.impl;

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
    public void connect() throws Exception {
        rpcReferenceBean.init();
    }

    @Override
    public API handler() {
        rpcReferenceBean.setAddress(address);
        rpcReferenceBean.setIface(API.class);
        API object = (API) rpcReferenceBean.getObject();
        return object;
    }

//    @Override
//    public void handler(Object obj) {
//
//    }

    //    protected static final transient Logger LOGGER = LoggerFactory.getLogger(TrayClient.class);
//
//    private static volatile ConcurrentMap<String, TrayClient> connectClientMap = new ConcurrentHashMap<>();
//    private static volatile ConcurrentMap<String, Object> connectClientLocked = new ConcurrentHashMap<>();
//
//    public abstract void init(String address) throws InterruptedException;
//
//    public abstract void close() throws InterruptedException;
//
//    public abstract boolean isActive();
//
//    public abstract void send(Object data) throws Exception;
//
//    /**
//     * async send
//     */
//    public static void asyncSend(Object data, String address, Class<? extends TrayClient> connectClient) throws Exception{
//        TrayClient client = TrayClient.getPool(address, connectClient);
//        try {
//            client.send(data);
//        } catch (Exception e) {
//            LOGGER.error(">>>>>TrayClient asyncSend exception:{}", e.getMessage());
//        }
//    }
//
//    private static TrayClient getPool(String address, Class<? extends TrayClient> connectClient) throws Exception{
//        TrayClient client = connectClientMap.get(address);
//        if (null != client && client.isActive()) {
//            return client;
//        }
//        Object lock = connectClientLocked.get(address);
//        if (null == lock) {
//            connectClientLocked.putIfAbsent(address, new Object());
//            lock = connectClientLocked.get(address);
//        }
//        synchronized (lock) {
//            client = connectClientMap.get(address);
//
//            if (null != client && client.isActive()) {
//                return client;
//            }
//            //remove old client
//            if (null != client) {
//                client.close();
//                connectClientMap.remove(address);
//            }
//            //set pool
//            TrayClient trayClient = connectClient.newInstance();
//            trayClient.init(address);
//            connectClientMap.put(address, trayClient);
//            return trayClient;
//        }
//    }

}
