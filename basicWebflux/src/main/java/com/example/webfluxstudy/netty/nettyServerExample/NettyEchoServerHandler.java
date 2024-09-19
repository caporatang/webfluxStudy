package com.example.webfluxstudy.netty.nettyServerExample;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Autowired
    private NettyServiceTest1 nettyServiceTest1;

    @Autowired
    private NettyServiceTest2 nettyServiceTest2;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Channel Read IN !!!!!!!!!!!");


        HttpRequest req = (HttpRequest) msg;

        String uri = req.uri();
        System.out.println("uri is : " + uri);

        if(uri.equals("/test/test")) {
            nettyServiceTest1.printHello();
        }
        if(uri.contains("taeil")) {
            nettyServiceTest2.printHello();
        }


        // Handler 구현
        if (msg instanceof String) {
            // echoServer 이기 떄문에 메세지가 String 이라면 그대로 bypass
            log.info("Received : {}", msg);
            ctx.writeAndFlush(msg)
                    .addListener(ChannelFutureListener.CLOSE); //
        }
    }
}