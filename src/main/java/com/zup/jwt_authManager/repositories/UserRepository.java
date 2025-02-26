package com.zup.jwt_authManager.repositories;

import com.zup.jwt_authManager.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
