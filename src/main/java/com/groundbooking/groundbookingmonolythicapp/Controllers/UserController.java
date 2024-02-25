package com.groundbooking.groundbookingmonolythicapp.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.groundbooking.groundbookingmonolythicapp.Entities.ApiResponse;
import com.groundbooking.groundbookingmonolythicapp.Entities.UserDetails;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.DataNotFound;
import com.groundbooking.groundbookingmonolythicapp.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
//@EnableMethodSecurity
public class UserController {
	@Autowired
	private UserDetailsService userDetailsService;
//	@Autowired
//	private RolesService rolesService;
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/allusers")
	public ResponseEntity<List<UserDetails>> getAllUsers(){
		List<UserDetails> allUsers= userDetailsService.getAllUsers();
		if(allUsers.size()<=0) {
			throw new DataNotFound("No Users","are registered ","us yet");
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(allUsers);
	}

	@GetMapping("/getuserbyid/{id}")
	public ResponseEntity<Optional<UserDetails>> getUserById(@PathVariable UUID id){
		Optional<UserDetails> user=userDetailsService.getUserById(id);
		if(user.isPresent())
			return ResponseEntity.status(HttpStatus.FOUND).body(user);
		throw new DataNotFound("No User","Found ","id:"+id.toString());
	}
	
	@GetMapping("/getuserbyemail/{email}")
	public ResponseEntity<UserDetails> getUserByEmail(@PathVariable String email){
		UserDetails user=userDetailsService.getUserByEmail(email);
		if(user!=null)
			return ResponseEntity.status(HttpStatus.FOUND).body(user);
		throw new DataNotFound("No User","is registered ","email:"+email);
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/adduser")
	public ResponseEntity<ApiResponse> addUser(@RequestBody UserDetails user) {
		String userFirstAndLastName=userDetailsService.addUser(user);
		ApiResponse apiResponse=ApiResponse.builder()
				.message(userFirstAndLastName+" added successfully")
				.success(true).build();
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
	}
	
	@PutMapping("/updateuser")
	public ResponseEntity<UserDetails> updateUser(@RequestBody UserDetails user){
		if(userDetailsService.getUserByEmail(user.getEmail())==null){
			throw new DataNotFound("No User","is registered ","email:"+user.getEmail());
		}
		UserDetails u=userDetailsService.updateUser(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(u);
	}

	@DeleteMapping("/deleteuser/{email}")
	public ResponseEntity<UserDetails> deleteUser(@PathVariable String email){
		UserDetails user=userDetailsService.getUserByEmail(email);
		if(user!=null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(userDetailsService.deleteUser(user.getUser_id()));
		}
		else
			throw new DataNotFound("No User","are registered ","email:"+email);
	}
	
//	@GetMapping("/allroles")
//	public ResponseEntity<List<Roles>> allRoles(){
//		return ResponseEntity.status(HttpStatus.FOUND).body(rolesService.getAllRoles());
//	}
//
//	@PostMapping("/addrole")
//	public ResponseEntity<Roles> addRole(@RequestBody Roles role){
//		return ResponseEntity.status(HttpStatus.ACCEPTED).body(rolesService.addRole(role));
//	}
}
