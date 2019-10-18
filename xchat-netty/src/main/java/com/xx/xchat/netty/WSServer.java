package com.xx.xchat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * WebSocket
 */
@Component
public class WSServer {

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;


    private static class SingletionWSServer{
        static final WSServer instance = new WSServer();
    }

    public static WSServer getInstance() {
        return SingletionWSServer.instance;
    }

    public WSServer() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new WebSocketChannelInitializer());
    }

    public void start() {
        this.channelFuture = serverBootstrap.bind(8899);
        System.err.println("netty websocket server 启动完毕..");
    }


}
