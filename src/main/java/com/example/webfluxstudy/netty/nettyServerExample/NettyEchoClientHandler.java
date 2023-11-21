package com.example.webfluxstudy.netty.nettyServerExample;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.netty.nettyServerExample
 * fileName : NettyEchoClientHandler
 * author : taeil
 * date : 11/21/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/21/23        taeil                   최초생성
 */
@Slf4j
public class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 클라이언트가 서버와의 연결이 준비 되었을 때
        ctx.writeAndFlush("This is client.");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            log.info("REceived: {}", msg);
        }
        // 다음 컨텍스트에 값을 보낼 필요가 없기 떄문에 해당 부분에서는 fireChannelRead는 사용하지 않는다.
    }
}