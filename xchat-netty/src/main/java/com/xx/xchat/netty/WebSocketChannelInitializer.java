package com.xx.xchat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // websocket 基于http协议，所以要有http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 对写大数据流的支持
        pipeline.addLast(new ChunkedWriteHandler());
        // netty对请求采取了分块或分段的方式，客户端向服务端发送了一个请求，请求的长度比如说是1000，以100为一段，那么就分为10段
        // 而HttpObjectAggregator就是把HTTP请求的几块聚合在一起
        // maxContentLength就是聚合的最大长度
        pipeline.addLast(new HttpObjectAggregator(8192));

        // ================================ 增加心跳支持 start ================================

        // 针对客户端，如果在1分钟时间内没有向服务端发送读写心跳(ALL)，则主动断开
        // 如果是读空闲或者写空闲，不处理
        pipeline.addLast(new IdleStateHandler(2, 4, 6));
        // 自定义的空闲状态检测
        pipeline.addLast(new HeartBeatHandler());

        // ================================ 增加心跳支持 end ================================

        // ws://server:port/context_path
        // ws://localhost:9999/ws
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
