package com.revature.services;

import java.util.List;

import com.revature.models.UserRole;

public interface UserRoleService {	
	
	public int register(UserRole userRole);
	
	public UserRole findUserRoleById(int id);
	
	public List<UserRole> findAllUserRoles();
	
	public boolean editUserRole(UserRole userRole);
	
	public boolean deleteUserRole(int id);
}
