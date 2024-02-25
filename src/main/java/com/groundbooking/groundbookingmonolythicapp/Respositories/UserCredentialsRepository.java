package com.groundbooking.groundbookingmonolythicapp.Respositories;

import com.groundbooking.groundbookingmonolythicapp.Entities.UserCredentials;
import org.springframework.stereotype.Repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials,UUID> {
	public UserCredentials findByUsername(String username);
}
