package com.xxxx.xchat.service.impl;

import com.xx.xchat.utils.route.consistenthash.AbstractConsistentHash;
import com.xx.xchat.utils.route.consistenthash.TreeMapConsistentHash;
import com.xxxx.xchat.service.RouteService;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.TreeMap;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-11-24 17:27
 */
public class RouteServiceImpl implements RouteService {

    @Autowired
    private CuratorFramework client;

    @Override
    public String getServerPath(String userId) throws Exception {
        List<String> nodes = client.getChildren().forPath("/");
        AbstractConsistentHash map = new TreeMapConsistentHash();
        String targetAddress = map.process(nodes, userId);
        return targetAddress;
    }

}
