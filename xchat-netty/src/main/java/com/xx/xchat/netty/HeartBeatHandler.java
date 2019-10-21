package com.xx.xchat.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 用于检测channel的心跳handler
 *
 * 继承ChannelInboundHandlerAdapter，从而不需要实现channelRead0方法
 * @author xieyaqi
 * @mail xieyaqi11@gmail.com
 * @date 2019-10-21 16:03
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空间
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;

            switch (event.state()) {
                case READER_IDLE:
                    System.out.println("读空闲");
                    break;
                case WRITER_IDLE:
                    System.out.println("写空闲");
                    break;
                case ALL_IDLE:
                    System.out.println("channel关闭前，user的数量为: " + TextWebSocketFrameHandler.clients.size());

                    // 关闭无用的channel，以防资源浪费
                    ctx.channel().close();
                    System.out.println("channel关闭后，user的数量为: " + TextWebSocketFrameHandler.clients.size());
                    break;
            }
        }
    }
}
