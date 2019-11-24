package com.xx.xchat.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-11-24 12:21
 */
@Configuration
public class ZkClient {

    @Value("${zk.timeout}")
    private Integer timeout;
    @Value("${zk.url}")
    private String url;
    @Value("${zk.workspace}")
    private String workspace;


    @Bean
    public CuratorFramework getClient() {
        RetryPolicy retryPolicy = new RetryNTimes(3, 5000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(url)
                .sessionTimeoutMs(timeout)
                .retryPolicy(retryPolicy)
                .namespace(workspace)
                .build();
        client.start();

        return client;
    }
}
