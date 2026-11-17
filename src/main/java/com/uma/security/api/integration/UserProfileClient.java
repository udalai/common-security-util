package com.uma.security.api.integration;

import com.uma.security.api.config.DXPApiFeignAuthConfig;
import com.uma.security.api.integration.bean.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userProfileClient", url = "${uxp.api.host}/identity/v1", configuration = DXPApiFeignAuthConfig.class)
public interface UserProfileClient {

    @GetMapping("/identity/user/{userName}")
    UserProfile getUser(@PathVariable("userName") String userName);
}
