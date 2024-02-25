package com.groundbooking.groundbookingmonolythicapp.Controllers;

import com.groundbooking.groundbookingmonolythicapp.Entities.Roles;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.DataNotFound;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.RecordAlreadyExistsException;
import com.groundbooking.groundbookingmonolythicapp.Services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @PostMapping("/addrole")
    public ResponseEntity<Roles> addRole(@RequestBody Roles role){
        if(rolesService.getRoleByName(role.getRoleName())!=null)
            throw new RecordAlreadyExistsException("Role","already exists","name:"+role.getRoleName());
        return ResponseEntity.status(HttpStatus.OK).body(rolesService.addRole(role));
    }

    @GetMapping("/allroles")
    public ResponseEntity<List<Roles>> allUsers(){
        List<Roles> roles=rolesService.allRoles();
        if(roles.size()<=0)
            throw new DataNotFound("No Roles","are registered","us yet");
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }
}
