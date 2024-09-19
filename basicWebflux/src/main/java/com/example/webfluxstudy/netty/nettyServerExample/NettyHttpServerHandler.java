package com.example.webfluxstudy.netty.nettyServerExample;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.netty.nettyServerExample
 * fileName : NettyHttpServerHandler
 * author : taeil
 * date : 11/21/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/21/23        taeil                   최초생성
 */

public class NettyHttpServerHandler extends ChannelInboundHandlerAdapter {

    private final NettyServiceTest1 nettyServiceTest1 = new NettyServiceTest1();
    private final NettyServiceTest2 nettyServiceTest2 = new NettyServiceTest2();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest) {
            // HttpObjectAggregator 객체를 핸들러에 등록했기 때문에 FullHttpRequest가 넘어옴
            var request = (FullHttpRequest) msg;

            String uri = request.uri();
            System.out.println("uri is : " + uri);

            if(uri.equals("/test/test")) {
                //System.out.println("====================== test print Hello !");
                nettyServiceTest1.printHello();

            }
            if(uri.contains("taeil")) {
                //System.out.println("====================== taeil print Hello !");
                nettyServiceTest2.printHello();
            }


            var response = new DefaultFullHttpResponse(request.protocolVersion(), HttpResponseStatus.OK);

            // 데이터 세팅
            response.headers().set("Content-type", "text/plain");
            // content는 ByteBuf 객체가 세팅 되어야한다.
            response.content().writeCharSequence("Hello, world~", StandardCharsets.UTF_8);

            ctx.writeAndFlush(response)
                    .addListener(ChannelFutureListener.CLOSE);
        }
    }
}