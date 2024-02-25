package com.groundbooking.groundbookingmonolythicapp.Entities;

import java.sql.Blob;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class GroundDetails {

	@Id
	@GeneratedValue
	@JsonIgnore
	private UUID ground_id;
	private String groundName;
	private String groundLocation;
	private Float price;
	private String description;
	private String addedBy;
	//@Lob
	//@Column(columnDefinition = "LONGBLOB")
//	@Column(length = 1000)
//	@Column(columnDefinition = "bytea[]")
	//@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private byte[] image;
}
