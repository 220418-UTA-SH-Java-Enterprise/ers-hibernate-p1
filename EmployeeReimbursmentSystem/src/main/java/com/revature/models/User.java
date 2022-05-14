package com.revature.models;

import javax.persistence.*;
import org.hibernate.annotations.NaturalId;
import lombok.*;

@Entity
@Table(name = "ers_users")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
	@Id
	@Column(name="ers_user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NaturalId
	@Column(name="ers_username")
	private String userName;
	
	@Column(name="ers_password")
	private String password;
	
	@Column(name="user_first_name")
	private String firstName;
	
	@Column(name="user_last_name")
	private String lastName;
	
	@Column(name="user_email")
	private String email;
	
	@ManyToOne
    @JoinColumn(name ="user_role_id", referencedColumnName = "ers_user_role_id")
	private UserRole userRole;

	public User() {
		super();
	}

	public User(String userName, String password, String firstName, String lastName, String email, UserRole userRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public User(int id, String userName, String password, String firstName, String lastName, String email,
			UserRole userRole) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userRole = userRole;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
}
