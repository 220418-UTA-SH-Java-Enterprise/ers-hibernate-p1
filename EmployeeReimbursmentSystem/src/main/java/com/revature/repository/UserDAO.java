package com.revature.repository;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	public int insert(User user);
	public User selectById(int id);
	public User selectByUserName(String userName);
	public List<User> selectAll();
	public boolean update(User user);
	public boolean delete(int id);
}
