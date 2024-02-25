package com.groundbooking.groundbookingmonolythicapp.Entities;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class UserDetails {
	@Id
	@GeneratedValue
	@Column(name="user_id")
	private UUID user_id;
	
	private String firstname;
	
	private String lastname;

	private String email;

	@JsonIgnore
	private String otpcode;

	private String created_by;

	private String modified_by;

	//@CreationTimestamp
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp created_at;

	//@CreationTimestamp
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
	private Timestamp modified_at;

	private String user_location;

	private String mobile_number;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date date_of_birth;

	private String secondary_emailid;
	
	//@JsonIgnore			// the compiler halts or return from from execution when @JsonIgnore is encountered so put @JsonIgnore in code that need to be fetched or executed last(In this case best to put in user_credentials)
	@JsonManagedReference(value = "userdetails-cred")			// we can also use @JsonIgnore at any one entity to avoid infinite looping instead of using @JsonManagedReference and @JsonBAckReference
	@OneToOne(mappedBy = "userDetails",cascade = CascadeType.ALL)
	private UserCredentials userCredentials;
	
//	@JsonManagedReference
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "user_roles")
//	private Roles roles;

	@Override		//when we are having bidirectional one to one mapping we must exclude the other entity from "toString()" method
	public String toString() {
		return "User_Details [user_id=" + user_id + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", otpcode=" + otpcode + ", created_by=" + created_by + ", modified_by=" + modified_by
				+ ", created_at=" + created_at + ", modified_at=" + modified_at + ", user_location=" + user_location
				+ ", mobile_number=" + mobile_number + ", date_of_birth=" + date_of_birth + ", secondary_emailid="
				+ secondary_emailid + "]";
	}
	
	
	
}
//@OneToMany(cascade = CascadeType.ALL)	
//@JoinColumn(name="fk_user_id",referencedColumnName = "user_id")
//private List<User_Credentials> userCredentials;

