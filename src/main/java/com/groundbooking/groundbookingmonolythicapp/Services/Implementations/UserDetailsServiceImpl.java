package com.groundbooking.groundbookingmonolythicapp.Services.Implementations;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.groundbooking.groundbookingmonolythicapp.Entities.ApiResponse;
import com.groundbooking.groundbookingmonolythicapp.Entities.UserCredentials;
import com.groundbooking.groundbookingmonolythicapp.Entities.UserDetails;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.RecordAlreadyExistsException;
import com.groundbooking.groundbookingmonolythicapp.Respositories.UserDetailsRepository;
import com.groundbooking.groundbookingmonolythicapp.Services.UserCredentialsService;
import com.groundbooking.groundbookingmonolythicapp.Services.UserDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDetailsRepository userDetailsRepository;
	@Autowired
	private UserCredentialsService userCredentialService;

	public List<UserDetails> getAllUsers() {
		List<UserDetails> allUsers = new ArrayList<>();
		userDetailsRepository.findAll().forEach(allUsers::add);
		return allUsers;
	}

	public Optional<UserDetails> getUserById(UUID id) {
		return userDetailsRepository.findById(id);
	}

	public UserDetails getUserByEmail(String email) {
		return userDetailsRepository.findByEmail(email);
	}

	public String addUser(UserDetails user) {
		if(getUserByEmail(user.getEmail())!=null)
			throw new RecordAlreadyExistsException("User","Already exits","email:"+user.getEmail());
		if (user.getCreated_at() == null) {
			// Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			Instant instant = Instant.now();
			user.setCreated_at(Timestamp.from(instant));
			// time here was displaying correctly but when storing in database was removing
			// 5 hours(going 5 hrs back)
		}
		if (user.getModified_at() == null) {
			// Timestamp timestamp=new Timestamp(System.currentTimeMillis());
			Instant instant = Instant.now();
			user.setModified_at(Timestamp.from(instant));
		}
		if (user.getCreated_by() == "") {
			user.setCreated_by("user"); // later change it to make it dynamic
		}
		if (user.getModified_by() == "") {
			user.setModified_by("user"); // later change it to make it dynamic
		}

		UserCredentials userCredentials=userCredentialService.addUserCredentials(user.getUserCredentials());
		userCredentials.setUserDetails(user);
		
		UserDetails useraddeddetails = userDetailsRepository.save(user);
		
		return useraddeddetails.getFirstname()+" "+useraddeddetails.getLastname();
	}

	public UserDetails updateUser(UserDetails user) {
		UserDetails userDetails=getUserByEmail(user.getEmail());
		user.setUser_id(userDetails.getUser_id());
		System.out.println("///////"+userDetails);
		user.setUserCredentials(userCredentialService.updateUserCredentials(user.getUserCredentials()));
		Instant instant = Instant.now();
		user.setModified_at(Timestamp.from(instant));
		user.setCreated_at(userDetails.getCreated_at());
		return userDetailsRepository.save(user);
//		UserDetails userDetails=getUserByEmail(user.getEmail());
//		BeanUtils.copyProperties(userDetails,user);
//		return userDetailsRepository.save(user);
	}

	@Override
	public UserDetails deleteUser(UUID id) {
		UserDetails user=getUserById(id).orElse(null);
		userDetailsRepository.deleteById(id);
		return user;
	}

}
