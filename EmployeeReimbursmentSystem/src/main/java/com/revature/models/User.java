package com.revature.models;

import java.util.Objects;

import javax.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "ers_users")
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

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, id, lastName, password, userName, userRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(userName, other.userName) && Objects.equals(userRole, other.userRole);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", userRole=" + userRole + "]";
	}
	
}
