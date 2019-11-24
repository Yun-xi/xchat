package com.xx.xchat.config;

import com.xx.xchat.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-11-24 16:50
 */
@Slf4j
@Component
public class ZkRegistry implements Runnable{

    @Value("${netty.port}")
    private Integer nettyPort;


    @Override
    public void run() {
        ZkClient zkClient = SpringUtil.getBean(ZkClient.class);
        CuratorFramework client = zkClient.getClient();

        try {
            //获得本机IP
            String ip = InetAddress.getLocalHost().getHostAddress();
            String route = ip + ":" + nettyPort;

            if (client.checkExists().forPath(route) == null) {
                client.create().creatingParentContainersIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(route);
            }
        } catch (Exception e) {
            log.error("zk初始化失敗");
        }
    }
}
