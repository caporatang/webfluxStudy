package com.example.webfluxstudy.netty.echoServer;

import com.example.webfluxstudy.basicReactiveProgramming.reactorPattern.exampleCode.Acceptor;
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
 * fileName : EchoServer
 * author : taeil
 * date : 11/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/18/23        taeil                   최초생성
 */
@Slf4j
public class NettyEchoServer {
    // channel에 Acceptor 등록

    // accept 이벤트를 처리하는 eventLoopGroup (parentGroup) 와 read 이벤트를 처리하는 eventLoopGroup(childGroup) 을 분리
    // parentGroup에 serverSOcketChannel 등록
    // channel의 pipeline에 acceptor를 등록

    public static void main(String[] args) {
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup(4);

        NioServerSocketChannel serverSocketChannel = new NioServerSocketChannel();

        // parentGroup에 serverSocketChannel을 등록 -> accept 이벤트를 받는다
        parentGroup.register(serverSocketChannel);

        // acceptor 메서드에 대한 동작이 끝나면 pipeline 에 context를 추가한다.
        // ParentGroup의 pipeline의 구조는 Head -> acceptor 로직을 처리 -> Tail 의 구조로 되어 있음.
        serverSocketChannel.pipeline().addLast(acceptor(childGroup));

        serverSocketChannel.bind(new InetSocketAddress(8080))
                .addListener(future -> {
                    if(future.isSuccess()) {
                        log.info("Server bound to port 8080");
                    }
                });
    }

    private static ChannelInboundHandler acceptor(EventLoopGroup childGroup) {
        // serverSocketChannel에 등록된 channelRead는 java nio에서 accept()를 통해서 socketCHannel을 얻는 것과 동일하다.
        // socketChannel에 LoggingHandler를 먼저 등록
        // echoHandler를 등록
        // childGroup (read 이벤트 담당) 에 socketChannel 등록

        var executorGroup = new DefaultEventExecutorGroup(4);

        return new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                // 클라이언트에서 보낸 값을 읽는 메서드
                // serverSocketChannel accept 이벤트가 channelRead를 통해서 전파된다.

                log.info("Acceptor.channelRead");
                if (msg instanceof SocketChannel) {
                    SocketChannel socketCHannel = (SocketChannel) msg; // -> accept 이벤트를 SocketChannel로 형변환
                    socketCHannel.pipeline().addLast(
                            // eventHandler에 있는 쓰레드가 아니라 별도의 쓰레드에서 LoggingHandler를 실행 시키겠다.
                            executorGroup, new LoggingHandler(LogLevel.INFO));
                    socketCHannel.pipeline().addLast(
                            // EchoHandler 등록
                            echoHandler()
                    );
                    // 파이프라인의 구조
                    // Header - LoggingHandler - echoHandler - Tail
                    // 이때, LoggingHandler는 들어오는 모든 이벤트들에 대해 출력함.

                    childGroup.register(socketCHannel);
                }
            }
        };
    }

    public static ChannelInboundHandler echoHandler() {
        // inbound된 message를 읽어서 출력
        // copy를 통해서 새로운 ByteBuf 생성
        // ctx.writeAndFlush를 통해서 다음 handler에게 메세지를 전달
        // 메세지 전달 이후 리소스 해제
        // ctx.writeAndFlush는 channelFUture를 반환. writeAndFlush가 완료되면 listener를 통해서 channel close 수행

        // out : pipeline의 구조
        // channelRead event를 아래 순서대로 넘겨줌 ehcoHandler로 값이 들어왔을때는 ByteBuf 형태로 들어온다.
        // Tail -> LoggingHandler -> echoHandler

        return new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                if(msg instanceof ByteBuf) {
                    try {
                        var buf = (ByteBuf) msg;
                        var len = buf.readableBytes();
                        var charset = StandardCharsets.UTF_8;
                        var body = buf.readCharSequence(len, charset); // 스트링으로 변환
                        log.info("EchoHandler. channelRead :{} ", body);

                        buf.readerIndex(0); // rewind -> Buf에 값을 읽어 들이면서 ByteBuf의 끝까지 가면서 읽어들였기 때문에 Buf의 값을 rewind
                        var result = buf.copy(); // 값을 카피해서 그대로 결과를 Flush하고 Flush가 끝난다면 채널을 close
                        ctx.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE);
                    } finally {
                        // 작업이 다 끝났으니 ByteBuf를 폐지 시켜야함 -> release
                        ReferenceCountUtil.release(msg);
                    }
                }
            }
        };
    }

    // I/O 이벤트, 작업 흐름
    // echoHandler 에서 ctx.fireChannelRead를 호출하지 않았기 떄문에 Tail context에게 read event 전달을 중단한다.
    // Head는 ChannelOutboundHandler를 구현했기 때문에 최종적으로 channel에 write하는 역할을 담당한다. -> Channel에 값을 쓰는 즉, 클라이언트에게 값을 전달하는 역할
    // I/O 이벤트는 Head -> loggingHandler -> echoHandler 순으로 전달한다.
    // I/O '작업'은 echoHandler -> loggingHandler -> Header 순으로 전달한다.



}