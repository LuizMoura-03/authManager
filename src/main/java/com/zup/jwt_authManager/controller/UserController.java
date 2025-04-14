package com.zup.jwt_authManager.controller;

import com.zup.jwt_authManager.controller.dtos.UserDTO;
import com.zup.jwt_authManager.models.UserModel;
import com.zup.jwt_authManager.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/admin")
    public ResponseEntity<String> adminAccess() {
        return ResponseEntity.ok("Bem-vindo ao endpoint ADMIN!");
    }

    @GetMapping("/user")
    public ResponseEntity<?> userAccess(Authentication authentication) {
        // Extraia o nome do usuário do objeto Authentication
        String username = authentication.getName();

        // Extraia o claim "department" do token JWT
        String department = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .filter(grantedAuthority -> grantedAuthority.getAuthority().startsWith("DEPARTMENT_"))
                .map(grantedAuthority -> grantedAuthority.getAuthority().replace("DEPARTMENT_", ""))
                .findFirst()
                .orElse("Unknown");

        // Retorne a resposta com o nome do usuario e o departamento
        return ResponseEntity.ok(Map.of(
                "message", "Bem-vindo, " + username + "!",
                "department", department
        ));
        }
}

