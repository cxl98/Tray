package com.easyArch.server.netty;

import com.easeArch.common.req.TrayRequest;
import com.easeArch.common.res.TrayResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class TRayNettyServerHandler extends SimpleChannelInboundHandler<TrayRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TrayRequest msg) throws Exception {
        System.out.println(msg);
        String id = msg.getId();
        Object value = msg.getValue();
        TrayResponse response=new TrayResponse();
        response.setId(id);
        response.setValue(value);
        response.setError("");
        ctx.writeAndFlush(response);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            System.out.println("idl    ");
        }
    }
}
