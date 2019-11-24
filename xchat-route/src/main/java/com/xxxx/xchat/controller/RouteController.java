package com.xxxx.xchat.controller;

import com.xx.xchat.utils.BaseResp;
import com.xxxx.xchat.common.CurrentUser;
import com.xxxx.xchat.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xieyaqi
 * @mail 987159036@qq.com
 * @date 2019-11-24 17:52
 */
@RestController
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping("/route")
    private ResponseEntity<BaseResp> route() throws Exception {
        String currentUserId = CurrentUser.getCurrentUserId();
        String serverPath = routeService.getServerPath(currentUserId);

        return ResponseEntity.ok(BaseResp.ok(serverPath));
    }
}
