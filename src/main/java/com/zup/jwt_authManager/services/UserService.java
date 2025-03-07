package com.zup.jwt_authManager.services;

import com.zup.jwt_authManager.controller.dtos.UserDTO;
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

    public UserModel registerUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("E-mail já existe!");
        }

        UserModel user = new UserModel();
        user.setUsername(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getEmail()));

        Set<RoleModel> roles = userDTO.getRoles().stream().
                map(roleName -> roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        user.setRoles(roles);

        return userRepository.save(user);

    }

}
