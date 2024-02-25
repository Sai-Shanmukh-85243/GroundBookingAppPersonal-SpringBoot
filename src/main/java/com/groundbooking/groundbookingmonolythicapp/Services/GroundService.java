package com.groundbooking.groundbookingmonolythicapp.Services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import com.groundbooking.groundbookingmonolythicapp.Entities.GroundDetails;
import org.springframework.web.multipart.MultipartFile;

public interface GroundService {
	public List<GroundDetails> getAllGrounds();
	public GroundDetails getGroundById(UUID id);
	public GroundDetails getGroundByName(String name);
	public List<GroundDetails> getGroundByAddedBy(String addedBy);
	public GroundDetails addGround(GroundDetails ground);
	public boolean addGround(String gName, String gLocation, Float price, String desc, String addedBy, MultipartFile file) throws IOException, SQLException;
	public GroundDetails updateGround(GroundDetails ground);
	public GroundDetails deleteGround(UUID id);
}
