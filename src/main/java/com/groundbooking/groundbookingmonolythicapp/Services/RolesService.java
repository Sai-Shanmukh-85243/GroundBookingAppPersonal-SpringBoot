package com.groundbooking.groundbookingmonolythicapp.Services;

import com.groundbooking.groundbookingmonolythicapp.Entities.Roles;

import java.util.List;

public interface RolesService {
    public Roles addRole(Roles role);
    public List<Roles> allRoles();
    public Roles getRoleByName(String name);
}
