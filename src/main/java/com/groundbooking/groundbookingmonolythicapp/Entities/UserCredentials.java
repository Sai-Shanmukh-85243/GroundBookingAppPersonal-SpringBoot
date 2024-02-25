package com.groundbooking.groundbookingmonolythicapp.Entities;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="user_credentials")
public class UserCredentials implements org.springframework.security.core.userdetails.UserDetails {

	@Id
	@GeneratedValue
	private UUID userCredentialsId;
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;


//@JsonIgnore
@JsonBackReference(value = "userdetails-cred")
@OneToOne(cascade = CascadeType.ALL)
@JoinColumn(name="fk_user_id")
private UserDetails userDetails;

//@JsonBackReference(value = "roles-usercreds")
@ManyToOne
private  Roles role;



@Override		//when we are having bidirectional one to one mapping we must exclude the other entity from "toString()" method
public String toString() {
	return "User_Credentials [userCredentialsId=" + userCredentialsId + ", username=" + username + ", password="
			+ password + "]";
}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//System.out.println("//||//"+List.of(new SimpleGrantedAuthority(role.getRoleName())));
		return List.of(new SimpleGrantedAuthority(role.getRoleName()));
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return true;
	}
}

//for one to many nothing needed in this entity

