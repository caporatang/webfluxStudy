package com.example.webfluxstudy.netty.encoderDecoderCodec;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.extern.slf4j.Slf4j;



/**
 * packageName : com.example.webfluxstudy.netty.encoderDecoderCodec
 * fileName : NettyCodecEchoServer
 * author : taeil
 * date : 11/20/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/20/23        taeil                   최초생성
 */
@Slf4j
public class NettyCodecEchoServer {
    // StringDecoder : ByteBuf 객체를 String으로 변경하여 다음 handler에게 제공
    // StringEncoder : String 객체를 ByteBuf로 변경하여 다음 handler에게 제공

    // echoHandler 앞에 stringEncoder, stringDecoder 적용
    // StringEncoder / Decoder는 Sharable annotation이 적용되어 있기 때문에 channel마다 재사용이 가능하다.
    private static ChannelInboundHandler acceptor(EventLoopGroup childGroup) {
        var executorGroup = new DefaultEventExecutorGroup(4);
        var stringEncoder = new StringEncoder();
        var stringDecoder = new StringDecoder();

        return new ChannelInboundHandlerAdapter(){
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                log.info("Acceptor.channelRead");
                if (msg instanceof SocketChannel) {
                    SocketChannel socketChannel = (SocketChannel) msg;
                    socketChannel.pipeline().addLast(
                            executorGroup, new LoggingHandler(LogLevel.INFO));
                    //socketChannel.pipeline().addLast(
                    //        stringEncoder, stringDecoder, echoHandler()))
                    childGroup.register(socketChannel);
                }

            }
        };
    }

}