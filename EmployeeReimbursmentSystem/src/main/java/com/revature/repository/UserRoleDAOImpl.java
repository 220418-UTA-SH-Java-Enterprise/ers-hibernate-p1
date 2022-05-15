package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.util.HibernateUtil;

public class UserRoleDAOImpl implements UserRoleDAO {
	
	private static Logger logger = Logger.getLogger(UserRoleDAOImpl.class);

	@Override
	public int insert(UserRole userRole) {
		logger.info("Adding a new user role to database. User info: " + userRole);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		int userId = (int) session.save(userRole);

		tx.commit();

		logger.info("insert successful! New user id is " + userId);

		return userId; // return the auto-generated userId
	}

	@Override
	public UserRole selectById(int id) {
		logger.info("searching user role by id: " + id);

		Session session = HibernateUtil.getSession();
//		String query = "SELECT * FROM ers_users WHERE id = " + id;
//		User user = (User) session.createNativeQuery(query, User.class)
//				.getSingleResult();
		
		IdentifierLoadAccess<UserRole> identifierObj = session.byId(UserRole.class);
		UserRole userRole = identifierObj.load(id);

		logger.info("Search complete! Found: " + userRole);

		return userRole;
	}

	@Override
	public List<UserRole> selectAll() {
		logger.info("Getting all user roles from database....");

		Session session = HibernateUtil.getSession();

		List<UserRole> userRoles = session.createQuery("from User", UserRole.class).list();
		logger.info("User list retrieved! Size: " + userRoles.size());

		return userRoles;
	}

	@Override
	public boolean update(UserRole userRole) {
		logger.info("Updating user role. User role info: " + userRole);

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.clear();

		session.update(userRole);

		tx.commit();

		logger.info("User role updated successfully!");

		return true;
	}

	@Override
	public boolean delete(UserRole userRole) {
		logger.info("Deleting user role. User role info: " + userRole);

		Session session = HibernateUtil.getSession();

		Transaction tx = session.beginTransaction();

		session.clear();

		session.delete(userRole);

		tx.commit();

		logger.info("Successfully deleted the user!");

		return true;
	}

}
