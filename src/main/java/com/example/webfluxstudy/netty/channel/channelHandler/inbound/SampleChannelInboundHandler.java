package com.example.webfluxstudy.netty.channel.channelHandler.inbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.StandardCharsets;

/**
 * packageName : com.example.webfluxstudy.netty.channel.channelHandler.inbound
 * fileName : SampleChannelInboundHandler
 * author : taeil
 * date : 11/18/23
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 11/18/23        taeil                   최초생성
 */
public class SampleChannelInboundHandler  extends ChannelInboundHandlerAdapter {
    // ChannelInboundHandlerAdapter -> ChannelInboundHandler 는 각각의 메서드의 인터페이스를 들고있기 떄문에 관심없는 채까지 다 구현해야함 , 반면 Adapter는 뼈대는 다 구현해놓은 클래스이기 떄문에 사용

    // Pipeline read 이벤트 전파
    // 현재의 context와 이전 값이 주어진다.
    // fireCchannelRead : 다음 context로 read 이벤트를 전달할 수 있다. msg를 변경해서 다른 값을 수정 후 전달 가능하다.
    // writeAndFlush : channel로 write 작업을 전달 할 수 있다.
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            // 다음 context로, Inbound로 이벤트를 전달하지 않고 outbound I/O 작업을 수행한 후 채널을 닫는다.
            ctx.writeAndFlush("Hello, " + msg)
                    .addListener(ChannelFutureListener.CLOSE);
        }
        else if (msg instanceof ByteBuf) {
            // 별도의 outbound I/O 작업을 수행하지 않고 다음 context로 이벤트를 전달한다.
            // msg를 변형해서 전달 가능하다.
            try {
                var buf = (ByteBuf) msg;
                var len = buf.readableBytes();
                var charset = StandardCharsets.UTF_8;
                var body = buf.readCharSequence(len, charset);
                ctx.fireChannelRead(body); // 이벤트를 fire 한다.
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }
    }
}