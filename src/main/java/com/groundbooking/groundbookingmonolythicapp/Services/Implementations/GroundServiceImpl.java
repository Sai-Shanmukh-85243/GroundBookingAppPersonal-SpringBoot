package com.groundbooking.groundbookingmonolythicapp.Services.Implementations;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.groundbooking.groundbookingmonolythicapp.Entities.GroundDetails;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.RecordAlreadyExistsException;
import com.groundbooking.groundbookingmonolythicapp.Respositories.GroundRespository;
import com.groundbooking.groundbookingmonolythicapp.Services.GroundService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;


@Service
public class GroundServiceImpl implements GroundService {
	@Autowired
	private GroundRespository groundRespository;

	@Override
	public List<GroundDetails> getAllGrounds() {
		// TODO Auto-generated method stub
		return groundRespository.findAll();
	}

	@Override
	public GroundDetails getGroundById(UUID id) {
		// TODO Auto-generated method stub
		return groundRespository.findById(id).orElse(null);
	}

	@Override
	public GroundDetails getGroundByName(String name) {
		// TODO Auto-generated method stub
		return groundRespository.findByGroundName(name);
	}

	@Override
	public List<GroundDetails> getGroundByAddedBy(String addedBy) {
		List<GroundDetails> grounds=groundRespository.findByAddedBy(addedBy);
		return grounds;
	}

	@Override
	public GroundDetails addGround(GroundDetails ground) {
		// TODO Auto-generated method stub
		return groundRespository.save(ground);
	}

	@Override
	public boolean addGround(String gName, String gLocation, Float price, String desc, String addedBy, MultipartFile file) throws IOException, SQLException {
		GroundDetails ground=new GroundDetails();
		ground.setGroundName(gName);
		ground.setGroundLocation(gLocation);
		ground.setPrice(price);
		ground.setAddedBy(addedBy);
		ground.setDescription(desc);
		if(file != null)
			ground.setImage(file.getBytes());
		else
			ground.setImage(null);

		try{
			if(getGroundByName(gName) != null){
				//System.out.println("throwing exception");
				return false;

			}
			else {
				groundRespository.save(ground);
				//System.out.println("returning true");
				return true;
			}

		}
		catch (Exception e){
			System.out.println(e);
			return false;
		}

	}


	@Override
	public GroundDetails updateGround(GroundDetails ground) {
		// TODO Auto-generated method stub
		ground.setGround_id(getGroundByName(ground.getGroundName()).getGround_id());
		return groundRespository.save(ground);
	}

	@Override
	public GroundDetails deleteGround(UUID id) {
		// TODO Auto-generated method stub
		GroundDetails ground=getGroundById(id);
		groundRespository.deleteById(id);
		return ground;
	}

}
