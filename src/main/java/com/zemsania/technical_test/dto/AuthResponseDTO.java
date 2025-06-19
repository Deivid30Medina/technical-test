package com.zemsania.technical_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {

    private String accessToken, refreshToken;
    private Integer id;
    private String username, email, firstName, lastName, gender, image;
}
