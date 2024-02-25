package com.groundbooking.groundbookingmonolythicapp.Services.Implementations;

import com.groundbooking.groundbookingmonolythicapp.Entities.Roles;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.DataNotFound;
import com.groundbooking.groundbookingmonolythicapp.Respositories.RoleRepository;
import com.groundbooking.groundbookingmonolythicapp.Services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImpl implements RolesService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Roles addRole(Roles role) {
        System.out.println(role);
        return roleRepository.save(role);
    }

    @Override
    public List<Roles> allRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Roles getRoleByName(String name){
        return roleRepository.findByRoleName(name);
    }
}
