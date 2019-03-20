package com.aibao.claims.auth.client;


import com.aibao.claims.auth.dto.User;
import com.aibao.claims.auth.result.R;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@FeignClient(name = "claims-auth")
public interface TestClient {


    /**
     * 获取数据库中的用户
     *
     * @param id
     * @return
     */
    @GetMapping("/user/get/{id}")
    public R<User> get(@PathVariable("id") int id) ;

}
