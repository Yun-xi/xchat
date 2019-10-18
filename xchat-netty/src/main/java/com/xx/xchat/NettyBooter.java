package com.xx.xchat;

import com.xx.xchat.netty.WSServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-10-18 22:54
 */
@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
            WSServer.getInstance().start();
        }
    }
}
