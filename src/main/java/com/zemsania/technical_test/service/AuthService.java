package com.zemsania.technical_test.service;

import com.zemsania.technical_test.dto.AuthRequestDTO;
import com.zemsania.technical_test.dto.AuthResponseDTO;
import com.zemsania.technical_test.dto.UserInfoDTO;

public interface AuthService {

    AuthResponseDTO login(AuthRequestDTO request);

    UserInfoDTO getMe(String cookie);
}
