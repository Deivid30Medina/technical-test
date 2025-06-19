package com.zemsania.technical_test.service.impl;

import com.zemsania.technical_test.client.DummyAuthClient;
import com.zemsania.technical_test.dto.AuthRequestDTO;
import com.zemsania.technical_test.dto.AuthResponseDTO;
import com.zemsania.technical_test.entity.LoginLog;
import com.zemsania.technical_test.repository.LoginLogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private DummyAuthClient client;

    @Mock
    private LoginLogRepository repo;

    @InjectMocks
    private AuthServiceImpl service;

    @Captor
    private ArgumentCaptor<LoginLog> loginLogCaptor;

    @Test
    void login_success_savesLog() {
        // Arrange
        AuthRequestDTO req = new AuthRequestDTO("michaelw", "michaelwpass");
        AuthResponseDTO resp = new AuthResponseDTO(
                "token123", "refresh456",
                2, "michaelw", "michael.williams@x.dummyjson.com",
                "Michael", "Williams", "male", "https://dummyjson.com/icon/michaelw/128"
        );
        when(client.login(req)).thenReturn(resp);

        // Act
        AuthResponseDTO result = service.login(req);

        // Assert: el resultado debe coincidir con el mock
        assertEquals("token123", result.getAccessToken());
        assertEquals("refresh456", result.getRefreshToken());

        // Verificamos que repo.save() fue llamado una vez
        verify(repo, times(1)).save(loginLogCaptor.capture());

        // Verificamos contenido del LoginLog
        LoginLog saved = loginLogCaptor.getValue();
        assertEquals("michaelw", saved.getUsername());
        assertEquals("token123", saved.getAccessToken());
        assertEquals("refresh456", saved.getRefreshToken());
        assertNotNull(saved.getLoginTime());
    }
}