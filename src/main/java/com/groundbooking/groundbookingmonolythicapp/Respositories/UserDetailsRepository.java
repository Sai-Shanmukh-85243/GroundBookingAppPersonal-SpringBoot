package com.groundbooking.groundbookingmonolythicapp.Respositories;

import java.util.UUID;

import com.groundbooking.groundbookingmonolythicapp.Entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, UUID> {
	
		public UserDetails findByEmail(String email);
}
