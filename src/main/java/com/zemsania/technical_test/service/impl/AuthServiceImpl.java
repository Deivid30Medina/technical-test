package com.zemsania.technical_test.service.impl;

import com.zemsania.technical_test.client.DummyAuthClient;
import com.zemsania.technical_test.dto.AuthRequestDTO;
import com.zemsania.technical_test.dto.AuthResponseDTO;
import com.zemsania.technical_test.dto.UserInfoDTO;
import com.zemsania.technical_test.entity.LoginLog;
import com.zemsania.technical_test.repository.LoginLogRepository;
import com.zemsania.technical_test.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final DummyAuthClient authClient;
    private final LoginLogRepository repository;

    @Override
    public AuthResponseDTO login(AuthRequestDTO request) {
        AuthResponseDTO answer = authClient.login(request);
        createLogLogin(request, answer);
        return answer;
    }

    /**
     * Stores a successful authentication attempt in the login log.
     * @param request - request the authentication request containing the username
     * @param answer - answer  the successful authentication response with tokens
     */
    private void createLogLogin(AuthRequestDTO request, AuthResponseDTO answer) {
        repository.save(new LoginLog(
                null,
                request.getUsername(),
                LocalDateTime.now(),
                answer.getAccessToken(),
                answer.getRefreshToken()
        ));
    }

    @Override
    public UserInfoDTO getMe(String cookie) {
        return authClient.getMe("accessToken=" + cookie);
    }
}
