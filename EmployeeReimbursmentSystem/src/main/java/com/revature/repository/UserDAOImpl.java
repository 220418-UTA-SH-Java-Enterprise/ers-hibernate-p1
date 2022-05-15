package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.util.HibernateUtil;

public class UserDAOImpl implements UserDAO {

	private static Logger logger = Logger.getLogger(UserDAOImpl.class);

	@Override
	public int insert(User user) {

		logger.info("Adding a new user to database. User info: " + user);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.clear();
		int userId = (int) session.save(user);

		tx.commit();

		logger.info("insert successful! New user id is " + userId);

		return userId; // return the auto-generated userId
	}

	@Override
	public User selectById(int id) {

		logger.info("searching user by id: " + id);

		Session session = HibernateUtil.getSession();
//		String query = "SELECT * FROM ers_users WHERE id = " + id;
//		User user = (User) session.createNativeQuery(query, User.class)
//				.getSingleResult();
		
		IdentifierLoadAccess<User> identifierObj = session.byId(User.class);
		User user = identifierObj.load(id);

		logger.info("Search complete! Found: " + user);

		return user;
	}

	@Override
	public User selectByUserName(String userName) {
		logger.info("searching user by firstName: " + userName);

		Session session = HibernateUtil.getSession();
//		String query = "SELECT * FROM ers_users WHERE ers_username = '" + userName + "'";
//		User user = (User) session
//				.createNativeQuery(query, User.class)
//				.getSingleResult();
		
		User user = session.byNaturalId(User.class).using("ers_username", userName).load();

		logger.info("Search complete! Found: " + user);

		return user;
	}

	@Override
	public List<User> selectAll() {
		logger.info("Getting all users from database....");

		Session session = HibernateUtil.getSession();

		List<User> users = session.createQuery("from User", User.class).list();
		logger.info("User list retrieved! Size: " + users.size());

		return users;
	}

	@Override
	public boolean update(User user) {
		logger.info("Updating user. User info: " + user);

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.clear();

		session.update(user);

		tx.commit();

		logger.info("User update successful!");

		return true;
	}

	@Override
	public boolean delete(int id) {
		
		User user = selectById(id);
		
		if (user != null) {
			
			logger.info("Deleting user. User info: " + user);
			
			Session session = HibernateUtil.getSession();
			
			Transaction tx = session.beginTransaction();
			
			session.clear();
			
			session.delete(user);
			
			tx.commit();
			
			logger.info("Successfully deleted the user!");
			
			return true;
		} else {
			logger.info("Delete failed: Unable to find a user with id: " + id);
			return false;	
		}
		
	}

}
