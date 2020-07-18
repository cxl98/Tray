//package com.easyArch.server.netty;
//
//import com.easeArch.common.codec.NettyDecoder;
//import com.easeArch.common.codec.NettyEncoder;
//import com.easeArch.common.req.TrayRequest;
//import com.easeArch.common.res.TrayResponse;
//import com.easeArch.common.util.PropertiesUtil;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.timeout.IdleStateHandler;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
//public class TrayNettyServer {
//
//
//    public void connect() throws IOException {
//        EventLoopGroup group = new NioEventLoopGroup();
//        EventLoopGroup work = new NioEventLoopGroup();
//        ServerBootstrap bootstrap = new ServerBootstrap();
//        try {
//            bootstrap.group(group, work).channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new IdleStateHandler(0, 0, 30, TimeUnit.SECONDS)).addLast(new NettyDecoder(TrayRequest.class))
//                                    .addLast(new NettyEncoder(TrayResponse.class))
//                                    .addLast(new TRayNettyServerHandler());
//                        }
//                    }).childOption(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_KEEPALIVE, true);
//            int port = PropertiesUtil.getInt("application.properties", "port");
//            ChannelFuture bind = bootstrap.bind(port).sync();
//
//            bind.sync().channel().closeFuture();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
