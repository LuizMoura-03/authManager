package com.zup.jwt_authManager.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private  String email;
    private Set<String> roles;
}
