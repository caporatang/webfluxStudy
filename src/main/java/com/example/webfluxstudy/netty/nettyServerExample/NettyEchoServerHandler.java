package com.example.webfluxstudy.netty.nettyServerExample;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.netty.nettyServerExample
 * fileName : NettyEchoServerHandler
 * author : taeil
 * date : 11/21/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/21/23        taeil                   최초생성
 */
@Slf4j
public class NettyEchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Handler 구현
        if (msg instanceof String) {
            // echoServer 이기 떄문에 메세지가 String 이라면 그대로 bypass
            log.info("Received : {}", msg);
            ctx.writeAndFlush(msg)
                    .addListener(ChannelFutureListener.CLOSE); //
        }
    }
}