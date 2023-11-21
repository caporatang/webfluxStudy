package com.example.webfluxstudy.netty.nettyServerExample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.netty.nettyServerExample
 * fileName : NettyEchoServer
 * author : taeil
 * date : 11/21/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/21/23        taeil                   최초생성
 */
@Slf4j
public class NettyEchoServer {

    @SneakyThrows
    public static void main(String[] args) {
        EventLoopGroup parentGroup = new NioEventLoopGroup(); // socketChannel accept 이벤트 처리 그룹
        EventLoopGroup childGroup = new NioEventLoopGroup(4); // socketChannel의 read 이벤트 그룹
        EventExecutorGroup eventExecutorGroup = new DefaultEventLoopGroup(4);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            var server = serverBootstrap
                    .group(parentGroup, childGroup) // accept , read
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            // accept 이벤트 끝난 후 read 이벤트 처리를 위한 파이프 라인의 새로운 핸들러를 추가 해주어야 함
                            ch.pipeline().addLast(
                                    // 처리해줄 스레드 그룹과 logging 핸들러를 파이프라인에 등록 -> INFO 이상의 로깅들이 출력
                                    eventExecutorGroup, new LoggingHandler(LogLevel.INFO)
                            );
                            ch.pipeline().addLast(
                                    // Encoder, Decoder -> EchoServer에 Encdoer 해서 값을 전달하고, Handler에서 내려온 스트링을 ByteBuf로 Decode해서 내려줄것임.
                                    new StringEncoder(),
                                    new StringDecoder(),
                                    // echoHandler는 Echo 처리를 할 것이기 떄문에 받은 String값을 그대로 넘겨줘야함
                                    new NettyEchoServerHandler()
                            );
                        }
                    });

            // 채널 바인드
            server.bind(8080).sync()
                    .addListener(new FutureListener<>() {
                        @Override
                        public void operationComplete(Future<Void> future) throws Exception {
                            if (future.isSuccess()) {
                                log.info("Success to bind 8080");
                            } else {
                                log.error("Fail to bind 8080");
                            }
                        }
                    }).channel().closeFuture().sync(); // 바로 종료시키지 않고 붙들고 있기 위함;
        } finally {
            // shutdown
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
            eventExecutorGroup.shutdownGracefully();
        }
    }
}