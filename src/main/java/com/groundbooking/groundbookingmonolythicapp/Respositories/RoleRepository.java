package com.groundbooking.groundbookingmonolythicapp.Respositories;

import com.groundbooking.groundbookingmonolythicapp.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Roles, UUID> {
    public Roles findByRoleName(String name);
}
