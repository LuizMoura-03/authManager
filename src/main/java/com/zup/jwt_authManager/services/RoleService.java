package com.zup.jwt_authManager.services;

import com.zup.jwt_authManager.models.RoleModel;
import com.zup.jwt_authManager.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleModel saveRole(RoleModel role) {
        return roleRepository.save(role);
    }

    public Optional<RoleModel> findByName(String name) {
        return roleRepository.findByName(name);
    }

    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}
