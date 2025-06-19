package com.zemsania.technical_test.service.impl;

import com.zemsania.technical_test.client.DummyAuthClient;
import com.zemsania.technical_test.dto.UsersResponse;
import com.zemsania.technical_test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final DummyAuthClient dummyClient;


    @Override
    public UsersResponse getUsers() {
        return dummyClient.getUsers();
    }
}
