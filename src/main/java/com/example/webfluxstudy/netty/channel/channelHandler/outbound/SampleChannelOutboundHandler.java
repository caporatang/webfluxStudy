package com.example.webfluxstudy.netty.channel.channelHandler.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.netty.channel.channelHandler.outbound
 * fileName : SampleChannelOutboundHandler
 * author : taeil
 * date : 11/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/18/23        taeil                   최초생성
 */
public class SampleChannelOutboundHandler extends ChannelOutboundHandlerAdapter {
    // outbound로 수행하는 I/O를 가로챌 수 있다.
    // 메세지를 변경하여 write가 가능하다.
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof String) {
            ctx.write(msg, promise);
        } else if (msg instanceof ByteBuf) {
            var buf = (ByteBuf) msg;
            var len = buf.readableBytes();
            var charset = StandardCharsets.UTF_8;
            var body = buf.readCharSequence(len,charset);
            ctx.write(body, promise);
        }

    }
}