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
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
        if (action == MsgActionEnum.CONNECT.type) {
            // 2.1 当websocket第一次open的时候，初始化channel，把用户的channel和userId关联起来
            String senderId = dataContent.getChatMsg().getSenderId();
            UserChannelRel.put(senderId, currentChannel);

            // TODO 测试
            for (Channel c : clients) {
                System.out.println(c.id().asLongText());
            }
            UserChannelRel.output();
        } else if (action == MsgActionEnum.CHAT.type) {
            // 2.2 聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态【未签收】
            ChatMsg chatMsg = dataContent.getChatMsg();
            String msgText = chatMsg.getMsg();
            String receiverId = chatMsg.getReceiverId();
            String senderId = chatMsg.getSenderId();

            // 保存消息到数据库，并且标记为【未签收】
            UserService userService = (UserService) SpringUtil.getBean("userServiceImpl");
            String msgId = userService.saveMsg(chatMsg);
            chatMsg.setMsgId(msgId);

            // 发送消息
            // 从全局用户Channel关系中获取接收方的channel
            Channel receiverChannel = UserChannelRel.get(receiverId);
            if (receiverChannel == null) {
                // TODO channel为空代表用户离线，推送消息(JPush，个推， 小米推送)

            } else {
                // 当receiverChannel不为空的时候，从ChannelGroup去查找对应的channel是否存在
                Channel findChannel = clients.find(receiverChannel.id());
                if (findChannel != null) {
                    // 用户在线
                    receiverChannel.writeAndFlush(new TextWebSocketFrame(JsonUtils.objectToJson(chatMsg)));
                } else {
                    // TODO 用户离线，推送
                }
            }
        } else if (action == MsgActionEnum.SIGNED.type) {
            // 2.3 签收消息类型，针对具体的消息进行签收，修改数据库中对应的消息的签收状态【已签收】
            UserService userService = (UserService) SpringUtil.getBean("userServiceImpl");
            // 扩展字段在signed类型的消息中，代表需要去签收的消息id，逗号分隔
            String msgIdStr = dataContent.getExtand();
            String[] msgIds = msgIdStr.split(",");

            List<String> msgIdList = new ArrayList<>();
            for(String msgId : msgIds) {
                if (StringUtils.isNotBlank(msgId)) {
                    msgIdList.add(msgId);
                }
            }

            System.out.println(msgIdList.toString());

            if (msgIdList != null && !msgIdList.isEmpty() && msgIdList.size() > 0) {
                userService.updateMsgSigned(msgIdList);
            }
        } else if (action == MsgActionEnum.KEEPALIVE.type) {
            // 2.4 心跳类型的消息

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
