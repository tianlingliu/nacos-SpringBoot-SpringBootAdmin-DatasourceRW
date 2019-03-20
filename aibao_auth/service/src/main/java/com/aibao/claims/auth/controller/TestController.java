package com.aibao.claims.auth.controller;


import com.aibao.claims.auth.client.TestClient;
import com.aibao.claims.auth.dto.User;
import com.aibao.claims.auth.mapper.entity.UserInfo;
import com.aibao.claims.auth.mapper.UserInfoMapper;
import com.aibao.claims.auth.result.ErrorCodeEnum;
import com.aibao.claims.auth.result.R;
import com.aibao.claims.auth.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RefreshScope
public class TestController implements TestClient {

    @Autowired
    private RedisService redisService;

    @Resource
    private UserInfoMapper userInfoMapper;


    private String nacosVersion;


    @RefreshScope
    @Value(value = "${nacos.version:test}")
    public void setNacosVersion(String nacosVersion) {
        this.nacosVersion = nacosVersion;
    }

    @PostMapping("/nacos")
    public R nacos() throws Exception {
        return R.ok(nacosVersion);
    }


    /**
     * 向redis存储值
     *
     * @param key
     * @param value
     * @return
     * @throws Exception
     */
    @PostMapping("/redis/set")
    public R set(String key, String value) throws Exception {

        redisService.set(key, value);
        return R.ok();
    }

    /**
     * 获取redis中的值
     *
     * @param key
     * @return
     */
    @PostMapping("/redis/get")
    public R<String> get(String key, int ms) {
        R<String> r = new R<>();
        try {
            Thread.sleep(ms);
            String value = redisService.get(key);
            return r.setData(value);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(ErrorCodeEnum.RC_500, e.getMessage());
        }
    }


    /**
     * 获取数据库中的用户
     *
     * @param id
     * @return
     */
    @Override
    @GetMapping("/user/get/{id}")
    public R<User> get(@PathVariable("id") int id) {
        R<User> r = new R<>();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
        User u = new User();
        BeanUtils.copyProperties(userInfo, u);
        r.setData(u);
        return r;
    }

}
