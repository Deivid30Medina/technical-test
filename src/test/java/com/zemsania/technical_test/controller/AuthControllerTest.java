package com.zemsania.technical_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemsania.technical_test.dto.AuthRequestDTO;
import com.zemsania.technical_test.dto.AuthResponseDTO;
import com.zemsania.technical_test.dto.UserInfoDTO;
import com.zemsania.technical_test.service.AuthService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void login_ValidRequest_ReturnsTokens() throws Exception {
        AuthRequestDTO request = new AuthRequestDTO("emilys", "emilyspass");
        AuthResponseDTO response = new AuthResponseDTO("token123", "refresh456",1,"emilys",
                "emily.johnson@x.dummyjson.com","Emily","Johnson","female","https://dummyjson.com/icon/emilys/128");

        when(authService.login(any(AuthRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("token123"))
                .andExpect(jsonPath("$.refreshToken").value("refresh456"));
    }

    @Test
    void me_ValidToken_ReturnsUserInfo() throws Exception {
        UserInfoDTO mockUser = new UserInfoDTO(1, "emilys", "emilyspass", "emily@example.com","Emily","Johnson");

        when(authService.getMe("valid-token")).thenReturn(mockUser);

        mockMvc.perform(get("/auth/me")
                        .cookie(new Cookie("accessToken", "valid-token")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("emilys"));
    }
}