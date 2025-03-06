package com.zup.jwt_authManager.controller.dtos;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}
