package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.UserRole;
import com.revature.util.HibernateUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	
	private static Logger log = Logger.getLogger(ReimbursementDAOImpl.class);

	@Override
	public int insert(Reimbursement reimbursement) {
		log.info("Adding a new reimbursement to the database. Reimbursement info: " + reimbursement);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		int id = (int) session.save(reimbursement);

		tx.commit();

		log.info("insert successful! New reimbursement id is " + id);

		return id; // return the auto-generated userId
	}

	@Override
	public Reimbursement selectById(int id) {
		log.info("searching reimbursement by id: " + id);

		Session session = HibernateUtil.getSession();
//		String query = "SELECT * FROM ers_users WHERE id = " + id;
//		User user = (User) session.createNativeQuery(query, User.class)
//				.getSingleResult();
		
		IdentifierLoadAccess<Reimbursement> identifierObj = session.byId(Reimbursement.class);
		Reimbursement reimbursement = identifierObj.load(id);

		log.info("Search complete! Found: " + reimbursement);

		return reimbursement;
	}

	@Override
	public List<Reimbursement> selectAll() {
		log.info("Getting all reimbursements from database....");

		Session session = HibernateUtil.getSession();

		List<Reimbursement> reimbursements = session.createQuery("from Reimbursement", Reimbursement.class).list();
		log.info("User list retrieved! Size: " + reimbursements.size());

		return reimbursements;
	}
	


	@Override
	public List<Reimbursement> selectByStatus(int statusId) {
		log.info("searching reimbursements by status: " + statusId);

		Session session = HibernateUtil.getSession();
		String query = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = " + statusId;
		List<Reimbursement> reimbursements = session.createNativeQuery(query, Reimbursement.class).getResultList();
				
		return reimbursements;
	}

	@Override
	public List<Reimbursement> selectByUserStatus(int userId, int statusId) {
		log.info("searching reimbursements by status: " + statusId);

		Session session = HibernateUtil.getSession();
		String query = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = " + statusId + " AND reimb_author = " + userId;
		List<Reimbursement> reimbursements = session.createNativeQuery(query, Reimbursement.class).getResultList();
				
		return reimbursements;
	}

	@Override
	public boolean update(Reimbursement reimbursement) {
		log.info("Updating user role. Reimbursement info: " + reimbursement);

		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		session.clear();

		session.update(reimbursement);

		tx.commit();

		log.info("Reimbursement updated successfully!");

		return true;
	}

	@Override
	public boolean delete(int id) {
		Reimbursement reimbursement = selectById(id);
		
		if (reimbursement != null) {
			log.info("Deleting user role. Reimbursement info: " + reimbursement);

			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			session.clear();

			session.delete(reimbursement);

			tx.commit();

			log.info("Reimbursement deleted successfully!");

			return true;
		} else {
			log.info("Delete failed: Unable to find a Reimbursement with id: " + id);
			return false;	
		}
		
	}

	@Override
	public List<Reimbursement> selectByUser(int userId) {
		log.info("searching reimbursements by user: " + userId);

		Session session = HibernateUtil.getSession();
		String query = "SELECT * FROM ers_reimbursement WHERE reimb_author = " + userId;
		List<Reimbursement> reimbursements = session.createNativeQuery(query, Reimbursement.class).getResultList();
				
		return reimbursements;
	}

}
