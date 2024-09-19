package com.example.webfluxstudy.netty.nettyServerExample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * packageName : com.example.webfluxstudy.netty.nettyServerExample
 * fileName : NettyEchoClient
 * author : taeil
 * date : 11/21/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/21/23        taeil                   최초생성
 */
@Slf4j
public class NettyEchoClient {
    // NettyEchoServer 와 다른점
    // 1. ServerBootstrap이 아니라 Bootstrap을 사용함
    // 2. parentGroup, childGroup 가 구분되지 않고 하나의 workGroup로 사용된다.
    // 3. bind가 아니라 connect로 연결

    @SneakyThrows
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup(1);

        try {
            Bootstrap bootstrap = new Bootstrap();
            var client = bootstrap
                        .group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<Channel>() {
                            // 여기서의 채널은 socketChannel이고, socketChannel이 connect가 되었을 때, 핸들링을 하겠다.
                            @Override
                            protected void initChannel(Channel ch) throws Exception {
                                ch.pipeline().addLast(
                                        new LoggingHandler(),
                                        new StringEncoder(),
                                        new StringDecoder(),
                                        new NettyEchoClientHandler()
                                );
                            }
                        });
            // closeFuture -> channel이 close가 될때까지 해당 부분에서 blocking이 되고 있을것임.
            client.connect("localhost", 8080)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}