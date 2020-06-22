//package com.easeArch.common.codec;
//
//
//
////import com.easeArch.common.serialization.ProtostuffSerializer;
//
//import com.cxl.rpc.serialize.impl.ProtostuffSerializer;
//import io.netty.buffer.ByteBuf;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.handler.codec.ByteToMessageDecoder;
//
//import java.util.List;
//
//public class NettyDecoder extends ByteToMessageDecoder {
//    private Class<?> genericClass;
//
//
//    public NettyDecoder(Class<?> genericClass) {
//        this.genericClass = genericClass;
//
//    }
//
//    @Override
//    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        if (in.readableBytes()<4){
//            return ;
//        }
//        in.markReaderIndex();
//        int dataLength=in.readInt();
//        if (dataLength<0){
//            ctx.close();
//        }
//        if (in.readableBytes()<dataLength) {
//            in.markReaderIndex();
//            return ;
//        }
//        byte [] data=new byte[dataLength];
//
//        in.readBytes(data);
//
//        Object obj= ProtostuffSerializer.deserializer(data,genericClass);
//        out.add(obj);
//    }
//}
