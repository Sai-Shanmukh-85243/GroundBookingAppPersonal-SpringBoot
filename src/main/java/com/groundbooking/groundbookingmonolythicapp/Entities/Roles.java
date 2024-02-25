package com.groundbooking.groundbookingmonolythicapp.Entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class Roles {
	@JsonIgnore
	@Id
	@Column(name="role_id")
	@GeneratedValue
	private UUID roleId;
	@Column(name="role_name")
	private String roleName;
	@JsonIgnore
	private String description;
	
//	@JsonManagedReference(value = "roles-usercreds")
//	@JsonIgnore
//	@ManyToMany
//	@JoinTable(name = "user_roles")
//	private List<UserCredentials> userCredentials;

	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", roleName=" + roleName + ", description=" + description + "]";
	}
	
	
}
