package com.groundbooking.groundbookingmonolythicapp.Services.Implementations;

import com.groundbooking.groundbookingmonolythicapp.Entities.UserCredentials;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.DataNotFound;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.RecordAlreadyExistsException;
import com.groundbooking.groundbookingmonolythicapp.Respositories.UserCredentialsRepository;
import com.groundbooking.groundbookingmonolythicapp.Services.RolesService;
import com.groundbooking.groundbookingmonolythicapp.Services.UserCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserCredentialsServiceImpl implements UserCredentialsService, UserDetailsService {
	
	@Autowired
	private UserCredentialsRepository userCredentialsRepository;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserCredentials addUserCredentials(UserCredentials usercred) {
		if(getUserByUsername(usercred.getUsername())!=null)
			throw new RecordAlreadyExistsException("User","already exists","username:"+usercred.getUsername());
		usercred.setPassword(passwordEncoder.encode(usercred.getPassword()));
		String rolename=usercred.getRole().getRoleName();
		usercred.setRole(rolesService.getRoleByName(rolename));
		return userCredentialsRepository.save(usercred);
	}
	
	public UserCredentials getUserByUsername(String username) {
		UserCredentials userCredentials=userCredentialsRepository.findByUsername(username);
//		commented below two lines since we will check for duplicate while adding username only so no change of dupicates
//		 (if we keep these two line exception (DataNotFound) getting raised when trying to add new credentials)

		//		if(userCredentials==null)
		//			throw new DataNotFound("Username"," "+username+" doesnot exists","us");
		return userCredentials;
	}

	@Override
	public UserCredentials updateUserCredentials(UserCredentials usercreds) {
		usercreds.setRole(rolesService.getRoleByName(usercreds.getRole().getRoleName()));
		usercreds.setUserCredentialsId(getUserByUsername(usercreds.getUsername()).getUserCredentialsId());
		return usercreds;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserCredentials usercreds=userCredentialsRepository.findByUsername(username);
		if(usercreds==null)
			throw new RuntimeException("User Not found");
		return usercreds;
	}


}
