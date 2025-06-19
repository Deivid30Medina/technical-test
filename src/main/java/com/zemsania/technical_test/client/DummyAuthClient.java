package com.zemsania.technical_test.client;

import com.zemsania.technical_test.config.DummyAuthFeignConfig;
import com.zemsania.technical_test.dto.AuthRequestDTO;
import com.zemsania.technical_test.dto.AuthResponseDTO;
import com.zemsania.technical_test.dto.UserInfoDTO;
import com.zemsania.technical_test.dto.UsersResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "dummyAuth", url = "${dummyjson.base-url}",configuration = DummyAuthFeignConfig.class)
public interface DummyAuthClient {

    @PostMapping("/auth/login")
    AuthResponseDTO login(@RequestBody AuthRequestDTO request);

    @GetMapping("/auth/me")
    UserInfoDTO getMe(@RequestHeader("Cookie") String cookie);

    @GetMapping("/users")
    UsersResponse getUsers();
}
