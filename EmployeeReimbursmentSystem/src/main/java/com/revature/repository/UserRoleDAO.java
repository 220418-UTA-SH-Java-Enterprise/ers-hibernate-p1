package com.revature.repository;

import java.util.List;

import com.revature.models.UserRole;

public interface UserRoleDAO {
	public int insert(UserRole userRole);
	public UserRole selectById(int id);	
	public List<UserRole> selectAll();
	public boolean update(UserRole userRole);
	public boolean delete(UserRole userRole);
}
