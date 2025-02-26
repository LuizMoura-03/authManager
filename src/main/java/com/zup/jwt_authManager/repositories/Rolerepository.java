package com.zup.jwt_authManager.repositories;

import com.zup.jwt_authManager.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Rolerepository extends JpaRepository<RoleModel, Long> {
}
