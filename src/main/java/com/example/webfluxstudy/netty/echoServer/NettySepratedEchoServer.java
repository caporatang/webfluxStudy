package com.example.webfluxstudy.netty.echoServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.netty.echoServer
 * fileName : NettySepratedEchoServer
 * author : taeil
 * date : 11/20/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/20/23        taeil                   최초생성
 */
@Slf4j
public class NettySepratedEchoServer {
    // 들어오는 ByteBuf를 String 으로 바꿔서 전달하는 ChannelInboundHandler 기반의 requestHandler 추가
    // 나가는 String을 ByteBuf로 바꿔서 전달하는 ChannelOutboundHandler 기반의 responseHandler 추가
    private static ChannelInboundHandler requestHandler() {
        return new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                if (msg instanceof ByteBuf) {
                    try {
                        var buf = (ByteBuf) msg;
                        var len = buf.readableBytes();
                        var charset = StandardCharsets.UTF_8;
                        var body = buf.readCharSequence(len, charset);
                        log.info("RequestHandler.channelRead: " + body);

                        ctx.fireChannelRead(body); // 다음 (next) inboundHandler에게 전달
                    } finally {
                        // ByteBuf를 release 해서 리소스를 해제한다.
                        ReferenceCountUtil.release(msg);
                    }
                }
            }
        };
    }

    private static ChannelOutboundHandler responseHandler() {
        // 이전 handler로부터 String을 받아서 buffer를 생성해서 값을 집어넣고, ctx.write를 호출하여 다음 handler에게 전달하는 역할.
        return new ChannelOutboundHandlerAdapter() {
            @Override
            public void write(
                    ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {

                if (msg instanceof String) {
                    log.info("ResponseHandler.write: " + msg);
                    var body = (String) msg;
                    var charset = StandardCharsets.UTF_8;
                    var buf = ctx.alloc().buffer();
                    buf.writeCharSequence(body, charset);
                    ctx.write(buf, promise);
                }
            }
        };
    }

    private static ChannelInboundHandler echoHandler() {
        // 변경된 echoHandler
        // String 타입의 msg를 수신.
        // 그대로 ctx.writeAndFlush를 통해서 다음(prev) outboundHandler 에게 전달한다.
        // addListener를 통해서 write 이 모두 끝나면 channel을 close -> 들어온 값을 그냥 바로 전달만 하는 역할로 변경된다.
        return new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                if (msg instanceof String) {
                    var request = (String) msg;
                    log.info("EchoHandler.channelRead: " + request);

                    ctx.writeAndFlush(request)
                            .addListener(ChannelFutureListener.CLOSE);
                }
            }
        };
    }

    private static ChannelInboundHandler acceptor(EventLoopGroup childGroup) {
        var executorGroup = new DefaultEventExecutorGroup(4);

        return new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) {
                log.info("Acceptor.channelRead");
                if (msg instanceof SocketChannel) {
                    SocketChannel socketChannel = (SocketChannel) msg;
                    socketChannel.pipeline().addLast(
                            executorGroup, new LoggingHandler(LogLevel.INFO));
                    socketChannel.pipeline().addLast(
                            requestHandler(),
                            responseHandler(),
                            echoHandler()
                    );
                    childGroup.register(socketChannel);
                }
            }
        };
    }

    public static void main(String[] args) {
        // echo 서버의 역할을 분리했다.
        // 그런데.. 매번 이렇게 많은 코드를 작성해야할까?
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup(4);

        NioServerSocketChannel serverSocketChannel = new NioServerSocketChannel();
        parentGroup.register(serverSocketChannel);
        serverSocketChannel.pipeline().addLast(acceptor(childGroup));

        serverSocketChannel.bind(new InetSocketAddress(8080))
                .addListener(future -> {
                    if (future.isSuccess()) {
                        log.info("Server bound to port 8080");
                    } else {
                        log.info("Failed to bind to port 8080");
                        parentGroup.shutdownGracefully();
                        childGroup.shutdownGracefully();
                    }
                });
    }
}