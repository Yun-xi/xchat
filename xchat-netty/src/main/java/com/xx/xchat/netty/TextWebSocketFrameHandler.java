package com.xx.xchat.netty;

import com.xx.xchat.enums.MsgActionEnum;
import com.xx.xchat.netty.domain.ChatMsg;
import com.xx.xchat.netty.domain.DataContent;
import com.xx.xchat.service.UserService;
import com.xx.xchat.utils.JsonUtils;
import com.xx.xchat.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDate;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的channel
    private static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        // 1.获取客户端传输过来的消息
        String content = msg.text();
        DataContent dataContent = JsonUtils.jsonToPojo(content, DataContent.class);
        Integer action = dataContent.getAction();

        Channel currentChannel = ctx.channel();

        // 2.判断消息类型，根据不同的类型来处理不同的业务
        // 2.1 当websocket第一次open的时候，初始化channel，把用户的channel和userId关联起来
        // 2.2 聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态【未签收】
        // 2.3 签收消息类型，针对具体的消息进行签收，修改数据库中对应的消息的签收状态【已签收】
        // 2.4 心跳类型的消息
        if (action == MsgActionEnum.CONNECT.type) {
            String senderId = dataContent.getChatMsg().getSenderId();
            UserChannelRel.put(senderId, currentChannel);
        } else if (action == MsgActionEnum.CHAT.type) {
            ChatMsg chatMsg = dataContent.getChatMsg();
            String msgText = chatMsg.getMsg();
            String receiverId = chatMsg.getReceiverId();
            String senderId = chatMsg.getSenderId();

            // 保存消息到数据库，并且标记为【未签收】
            UserService userService = (UserService) SpringUtil.getBean("userServiceImpl");

        } else if (action == MsgActionEnum.SIGNED.type) {

        } else if (action == MsgActionEnum.KEEPALIVE.type) {

        }


    }

    /**
     * 当客户端连接服务端后（打开连接）
     * 获取客户端的channel，并且放到ChannelGroup中去进行处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        clients.add(channel);
        System.out.println("handlerAdder: " + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
//        clients.remove(ctx.channel());
        System.out.println("handlerRemoved, 长id： " + ctx.channel().id().asLongText());
        System.out.println("handlerRemoved, 短id： " + ctx.channel().id().asShortText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生");
        ctx.close();
        clients.remove(ctx.channel());
    }
}
