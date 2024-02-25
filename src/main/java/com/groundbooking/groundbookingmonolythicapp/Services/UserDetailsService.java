package com.groundbooking.groundbookingmonolythicapp.Services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.groundbooking.groundbookingmonolythicapp.Entities.UserDetails;


public interface UserDetailsService {
	public List<UserDetails> getAllUsers();
	public Optional<UserDetails> getUserById(UUID id);
	public UserDetails getUserByEmail(String email);
	public String addUser(UserDetails user) ;
	public UserDetails updateUser(UserDetails user);
	public UserDetails deleteUser(UUID id);
}
