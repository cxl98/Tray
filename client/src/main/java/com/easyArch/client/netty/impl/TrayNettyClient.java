//package com.easyArch.client.netty.impl;
//
//import com.cxl.rpc.util.IpUtil;
//import com.easeArch.common.codec.NettyDecoder;
//import com.easeArch.common.codec.NettyEncoder;
//import com.easeArch.common.req.TrayRequest;
//import com.easeArch.common.res.TrayResponse;
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.timeout.IdleStateHandler;
//
//import java.util.concurrent.TimeUnit;
//
//public class TrayNettyClient extends TrayClient {
//
//    private Channel channel;
//
//    @Override
//    public void init(String address) throws InterruptedException {
//        final Bootstrap bootstrap=new Bootstrap();
//        EventLoopGroup group=new NioEventLoopGroup();
//        try {
//            bootstrap.group(group).channel(NioSocketChannel.class)
//                    .option(ChannelOption.TCP_NODELAY,true)
//                    .option(ChannelOption.SO_KEEPALIVE,true)
//                    .handler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new IdleStateHandler(0,0,30, TimeUnit.SECONDS))
//                                    .addLast(new NettyEncoder(TrayRequest.class))
//                                    .addLast(new NettyDecoder(TrayResponse.class))
//                                    .addLast(new TrayNettyClientHandler());
//                        }
//                    });
////            String host = PropertiesUtil.getString("application.properties", "ip");
////            int port = PropertiesUtil.getInt("application.properties", "port");
//            Object[] array = IpUtil.parseIpPort(address);
//            String host = (String) array[0];
//            int port = (int) array[1];
//            channel=bootstrap.connect(host, port).sync().channel();
//        } catch (InterruptedException e) {
//            LOGGER.error(">>>>>>TrayNettyClient exception:{}",e.getMessage());
//        }finally {
//            if (!isActive()){
//                close();
//            }
//        }
//    }
//
//    @Override
//    public void close() throws InterruptedException {
//        if (null!=channel&&channel.isActive()){
//            channel.closeFuture().sync();
//        }
//        LOGGER.debug("netty client close");
//    }
//
//    @Override
//    public boolean isActive() {
//        if (null!=channel){
//            return channel.isActive();
//        }
//        return false;
//    }
//
//    public void send(Object data) throws InterruptedException {
//        this.channel.writeAndFlush(data).sync();
//    }
//}
