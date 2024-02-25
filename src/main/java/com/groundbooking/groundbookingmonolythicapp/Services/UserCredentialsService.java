package com.groundbooking.groundbookingmonolythicapp.Services;

import com.groundbooking.groundbookingmonolythicapp.Entities.UserCredentials;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserCredentialsService {
	public UserCredentials addUserCredentials(UserCredentials usercreds);
	public UserCredentials getUserByUsername(String username);
	public UserCredentials updateUserCredentials(UserCredentials usercreds);
}
