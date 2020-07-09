//package com.test;
//
//import com.cxl.rpc.remoting.invoker.call.CallType;
//import com.cxl.rpc.remoting.invoker.reference.RpcReferenceBean;
//import com.cxl.rpc.remoting.invoker.route.LoadBalance;
//import com.cxl.rpc.remoting.net.NetEnum;
//import com.cxl.rpc.serialize.Serializer;
//import com.easeArch.common.entry.User;
//import com.easeArch.common.service.API;
//
//public class TestPro {
//    public static void main(String[] args) throws Exception {
////        String ip = PropertiesUtil.getString("application.properties", "ip");
////        System.out.println(ip);
////        Client client=new NettyClient();
////        TrayRequest request=new TrayRequest();
////        request.setId(UUID.randomUUID().toString());
////        request.setValue("nihao");
////        client.asyncSend(request,"127.0.0.1:8080");
//        User user=new User();
//        user.setUsername("xxx");
//        user.setPassword("xxxx");
//        API api= (API) new RpcReferenceBean(NetEnum.NETTY, Serializer.SerializerEnum.PROTOSTUFF.getSerializer(), CallType.SYNC, LoadBalance.ROUND,API.class,null,500,"127.0.0.1:8080",null,null,null).getObject();
//    }
//}
