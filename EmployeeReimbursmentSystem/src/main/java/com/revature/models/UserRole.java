package com.revature.models;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "ers_user_roles")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserRole {

	@Id
	@Column(name = "ers_user_role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_role")
	private String role;

	public UserRole() {
		super();
	}

	public UserRole(String role) {
		super();
		this.role = role;
	}

	public UserRole(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}
	
}
