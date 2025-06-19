package com.zemsania.technical_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersResponse {

    private List<UserInfoDTO> users;

    private Integer total;

    private Integer skip;

    private Integer limit;
}
