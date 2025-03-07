package com.zup.jwt_authManager.controller;

import com.zup.jwt_authManager.controller.dtos.AuthResponseDTO;
import com.zup.jwt_authManager.controller.dtos.LoginRequestDTO;
import com.zup.jwt_authManager.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> loginController(@RequestBody LoginRequestDTO newLogin) {
        String token = loginService.login(newLogin);

        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

}
