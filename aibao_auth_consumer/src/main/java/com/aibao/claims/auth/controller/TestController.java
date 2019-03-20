package com.aibao.claims.auth.controller;


import com.aibao.claims.auth.client.TestClient;
import com.aibao.claims.auth.dto.User;
import com.aibao.claims.auth.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RefreshScope
public class TestController {


    @Resource
    private TestClient testClient;


    @GetMapping("/user/get/{id}")
    public R<User> get(@PathVariable("id") int id) {
        R<User> r = new R<>();

        r = testClient.get(id);
        return r;
    }

}
