package com.zemsania.technical_test.controller;

import com.zemsania.technical_test.dto.AuthRequestDTO;
import com.zemsania.technical_test.dto.AuthResponseDTO;
import com.zemsania.technical_test.dto.UserInfoDTO;
import com.zemsania.technical_test.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO authRequestDTO){
        AuthResponseDTO responseDTO = service.login(authRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfoDTO> me(@CookieValue("accessToken") String cookie){
        UserInfoDTO userInfoDTO = service.getMe(cookie);
        return ResponseEntity.ok().body(userInfoDTO);
    }
}
