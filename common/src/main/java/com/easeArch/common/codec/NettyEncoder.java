//package com.easeArch.common.codec;
//
//import com.easeArch.common.serialization.ProtostuffSerializer;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.MessageToByteEncoder;
//
//public class NettyEncoder extends MessageToByteEncoder<Object> {
//    private Class<?> genericClass;
//
//    public NettyEncoder(Class<?> genericClass) {
//        this.genericClass = genericClass;
//    }
//
//    @Override
//    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
//        if (genericClass.isInstance(msg)){
//            byte[] data= ProtostuffSerializer.serializer(msg);
//            out.writeInt(data.length);
//            out.writeBytes(data);
//        }
//    }
//}
