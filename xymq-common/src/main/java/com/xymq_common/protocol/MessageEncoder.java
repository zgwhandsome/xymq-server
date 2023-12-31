package com.xymq_common.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author 张国伟
 * @date 2022年07月07日 16:52
 */
public class MessageEncoder extends MessageToByteEncoder<Protocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Protocol protocol, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(protocol.getLen());
        byteBuf.writeBytes(protocol.getContent());
    }
}
