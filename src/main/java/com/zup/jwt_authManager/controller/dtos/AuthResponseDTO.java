package com.zup.jwt_authManager.controller.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AuthResponseDTO {
    private String token;

}
