package com.zemsania.technical_test.controller;

import com.zemsania.technical_test.dto.UserInfoDTO;
import com.zemsania.technical_test.dto.UsersResponse;
import com.zemsania.technical_test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UsersResponse> getUsers(){
        UsersResponse userInfoDTOS = userService.getUsers();
        return ResponseEntity.ok(userInfoDTOS);
    }
}
