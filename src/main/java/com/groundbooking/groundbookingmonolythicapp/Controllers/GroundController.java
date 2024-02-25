package com.groundbooking.groundbookingmonolythicapp.Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import com.groundbooking.groundbookingmonolythicapp.Entities.ApiResponse;
import com.groundbooking.groundbookingmonolythicapp.Entities.GroundDetails;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.DataNotFound;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.CustomExceptions.RecordAlreadyExistsException;
import com.groundbooking.groundbookingmonolythicapp.ExceptionHandling.ExceptionResponse;
import com.groundbooking.groundbookingmonolythicapp.Services.GroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@RestController
@RequestMapping("/ground")
//@EnableMethodSecurity
//@CrossOrigin(origins = "http://localhost:4200")
public class GroundController {
	@Autowired
	private GroundService groundService;

	@GetMapping("/allgrounds")
	public ResponseEntity<List<GroundDetails>> getAllGrounds(){
		List<GroundDetails> allgrounds=groundService.getAllGrounds();
		if(allgrounds.size()<=0)
			throw new DataNotFound("No Ground","are registered ","us yet");

		//Making All the images null for now because in react native it is taking a lot of time to get images and hence metro was disconnecting.Will remove this later
//		allgrounds.forEach((i)->{
//			i.setImage(null);
//		});
		return new ResponseEntity<List<GroundDetails>>(allgrounds,HttpStatus.OK);
		//return ResponseEntity.status(HttpStatus.FOUND).body(allgrounds);
	}

	@GetMapping("/getgroundbyid/{id}")
	public ResponseEntity<GroundDetails> getGroundById(@PathVariable UUID id){
		GroundDetails ground=groundService.getGroundById(id);
		if(ground!=null)
			return  ResponseEntity.status(HttpStatus.FOUND).body(ground);
		throw new DataNotFound("Ground","not found","id:"+id.toString());
	}

//	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getgroundbyname")
	public ResponseEntity<GroundDetails> getGroundByName(@RequestParam String name){
		GroundDetails ground=groundService.getGroundByName(name);
		if(ground!=null)
			return ResponseEntity.status(HttpStatus.OK).body(ground);
		throw new DataNotFound("Ground","not found","name:"+name);
	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getgroundbyaddedby/{username}")
	public ResponseEntity<List<GroundDetails>> getGroundByAddedBy(@PathVariable String username){
		List<GroundDetails> ground=groundService.getGroundByAddedBy(username);
		return ResponseEntity.status(HttpStatus.OK).body(ground);
	}



	@PostMapping("/addground")
	public ResponseEntity<ApiResponse> addBooking(@RequestParam("groundName") String groundName,
											 @RequestParam("groundLocation") String groundLocation,
											 @RequestParam("price") Float price,
											 @RequestParam("description") String desc,
											 @RequestParam("addedBy") String addedBy,
											 @RequestParam(value = "image",required = false) MultipartFile image
	) throws SQLException, IOException {

			if (groundService.addGround(groundName, groundLocation, price, desc, addedBy, image)) {
				ApiResponse apiResponse=ApiResponse.builder()
						.message("Ground added Successfully")
						.success(true).build();
				return new ResponseEntity<>(apiResponse,HttpStatus.OK);
			} else {
				throw new RecordAlreadyExistsException("Ground","Already exists","name:"+groundName);
			}

	}

	//@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/updateground")
	public ResponseEntity<GroundDetails> updateGround(@RequestParam("groundName") String groundName,
													  @RequestParam("groundLocation") String groundLocation,
													  @RequestParam("price") Float price,
													  @RequestParam("description") String desc,
													  @RequestParam("addedBy") String addedBy,
													  @RequestParam(name = "image",required = false) MultipartFile image) throws IOException {


		GroundDetails tempDetails=groundService.getGroundByName(groundName);
		if(tempDetails==null)
			throw new DataNotFound("Ground","not found","name:"+groundName+".If you want to update name,delete thsi ground and create a new ground");
		tempDetails.setGroundLocation(groundLocation);
		tempDetails.setPrice(price);
		tempDetails.setDescription(desc);
		if(image != null){
			tempDetails.setImage(image.getBytes());
		}
		return ResponseEntity.status(HttpStatus.OK).body(groundService.updateGround(tempDetails));
	}
	
	@DeleteMapping("/deleteground")
	public ResponseEntity<GroundDetails> deleteGround(@RequestParam("groundName") String name){
		//System.out.println(name);
		GroundDetails tempDetails=groundService.getGroundByName(name);
		//System.out.println(tempDetails);
		if(tempDetails==null)
			throw new DataNotFound("Ground","not found","name:"+name);
		try{
			GroundDetails groundDetails = groundService.deleteGround(tempDetails.getGround_id());
			return ResponseEntity.status(HttpStatus.OK).body(groundDetails);
		}
		catch (DataIntegrityViolationException e){
			throw  new DataIntegrityViolationException("There are some active booking on this ground");
		}
	}
}
