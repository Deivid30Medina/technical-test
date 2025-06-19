package com.zemsania.technical_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private Integer id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;
}
