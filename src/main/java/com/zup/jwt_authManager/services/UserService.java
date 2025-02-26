package com.zup.jwt_authManager.services;

import com.zup.jwt_authManager.models.RoleModel;
import com.zup.jwt_authManager.models.UserModel;
import com.zup.jwt_authManager.repositories.RoleRepository;
import com.zup.jwt_authManager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserModel saveUser(UserModel user, Set<String> roleNames) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // codifica a senha.
        Set<RoleModel> roles = roleNames.stream()
                .map(roleName -> roleRepository.findByName(roleName)  // Busca os papeis pelo nome.
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
        .collect(Collectors.toSet());

        user.setRoles(roles); //associa papeis aos usuarios

        return userRepository.save(user);

    }

}
