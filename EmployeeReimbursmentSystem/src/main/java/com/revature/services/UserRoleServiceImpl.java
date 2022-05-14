package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.UserRole;
import com.revature.repository.UserDAOImpl;
import com.revature.repository.UserRoleDAO;
import com.revature.repository.UserRoleDAOImpl;

public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleDAO userRoleDao;
	private static Logger log = Logger.getLogger(UserRoleServiceImpl.class);

	public UserRoleServiceImpl(UserRoleDAOImpl userRoleDaoImpl) {
		super();
		this.userRoleDao = userRoleDaoImpl;
	}

	@Override
	public int register(UserRole userRole) {
		log.info("in service layer. Registering user role: " + userRole);
		return userRoleDao.insert(userRole);
	}

	@Override
	public UserRole findUserRoleById(int id) {
		log.info("in service layer. searching user role by id: " + id);
		return userRoleDao.selectById(id);
	}

	@Override
	public List<UserRole> findAllUserRoles() {
		log.info("in service layer. finding all user roles...");
		return userRoleDao.selectAll();
	}

	@Override
	public boolean editUserRole(UserRole userRole) {
		log.info("in service layer. editing user role: " + userRole);
		return userRoleDao.update(userRole);
	}

	@Override
	public boolean deleteUserRole(UserRole userRole) {
		log.info("in service layer. deleting user role: " + userRole);
		return userRoleDao.delete(userRole);
	}

}
