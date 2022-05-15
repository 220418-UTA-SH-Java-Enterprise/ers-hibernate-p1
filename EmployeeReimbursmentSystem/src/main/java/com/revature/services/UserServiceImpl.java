package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.models.User;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOImpl;

public class UserServiceImpl implements UserService {

	private UserDAO userDao;
	private static Logger log = Logger.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserDAOImpl userDaoImpl) {
		super();
		this.userDao = userDaoImpl;
	}

	@Override
	public User login(String username, String password) {
		log.info("in service layer. Logging in user with creds: " + username + ", " + password);

		Optional<User> users = userDao.selectAll()
				.stream()
				.filter(u -> (u.getUserName().equals(username) && u.getPassword().equals(password)))
				.findFirst();

		return users.isPresent() ? users.get() : null;
	}

	@Override
	public int register(User user) {
		log.info("in service layer. Registering user: " + user);
		return userDao.insert(user);
	}

	@Override
	public User findUserById(int id) {
		log.info("in service layer. searching user by id: " + id);
		return userDao.selectById(id);
	}

	@Override
	public List<User> findAllUsers() {
		log.info("in service layer. finding all users...");
		return userDao.selectAll();
	}

	@Override
	public boolean editUser(User user) {
		log.info("in service layer. editing user: " + user);
		return userDao.update(user);
	}

	@Override
	public boolean deleteUser(int id) {
		log.info("in service layer. removing user by id: " + id);
		return userDao.delete(id);
	}

}
