package com.xxxx.xchat.service;

import org.springframework.stereotype.Service;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-11-24 17:27
 */
@Service
public interface RouteService {

    String getServerPath(String userId) throws Exception;
}
