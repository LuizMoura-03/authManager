package com.zup.jwt_authManager.controller;

import com.zup.jwt_authManager.controller.dtos.UserDTO;
import com.zup.jwt_authManager.models.UserModel;
import com.zup.jwt_authManager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        UserModel user = userService.registerUser(userDTO);
        return ResponseEntity.ok("Usuário registrado com sucesso! ID: " + user.getId());
    }

    @GetMapping
    public ResponseEntity<String> showAccess() {
        return ResponseEntity.ok("Você acessou o endpoint GET /user");
    }
}

