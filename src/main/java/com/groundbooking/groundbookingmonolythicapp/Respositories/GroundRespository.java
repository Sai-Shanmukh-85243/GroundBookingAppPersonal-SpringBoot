package com.groundbooking.groundbookingmonolythicapp.Respositories;

import java.util.List;
import java.util.UUID;

import com.groundbooking.groundbookingmonolythicapp.Entities.GroundDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroundRespository extends JpaRepository<GroundDetails, UUID> {
	public GroundDetails findByGroundName(String name);
	public List<GroundDetails> findByAddedBy(String addedBy);
}
