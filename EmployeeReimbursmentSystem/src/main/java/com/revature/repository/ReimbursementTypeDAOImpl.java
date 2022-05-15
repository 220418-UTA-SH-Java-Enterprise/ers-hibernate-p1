package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ReimbursementType;
import com.revature.util.HibernateUtil;

public class ReimbursementTypeDAOImpl implements ReimbursementTypeDAO {

	private static Logger logger = Logger.getLogger(ReimbursementTypeDAOImpl.class);

	@Override
	public int insert(ReimbursementType reimbursementType) {
		logger.info("Adding a new reimbursement type to database. reimbursement type info: " + reimbursementType);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();

		int reimbursementId = (int) session.save(reimbursementType);

		tx.commit();

		logger.info("insert successful! New Reimbursement id is " + reimbursementId);

		return reimbursementId; // return the auto-generated reimbursementTypeId
	}

	@Override
	public ReimbursementType selectById(int id) {
		logger.info("searcing reimbursement type by id: " + id);

		Session session = HibernateUtil.getSession();
		// String query = "SELECT * FROM ers_reimbursement WHERE id = " id;
		// Reimbursement reimbursement = (reimbursement)
		// session.createNativeQuery(query, reimbursement.class)
		// .getSingleResult();

		IdentifierLoadAccess<ReimbursementType> identifierObj = session.byId(ReimbursementType.class);
		ReimbursementType reimbursementType = identifierObj.load(id);

		logger.info("Search complete! Found: " + reimbursementType);
		return reimbursementType;
	}

	@Override
	public List<ReimbursementType> selectAll() {
		logger.info("Getting all reimbursement types from database....");
		
		Session session = HibernateUtil.getSession();
		
		List<ReimbursementType> reimbursementType = session.createQuery("from Reimbursement", ReimbursementType.class).list();
		logger.info("Reimbursement type list retrieved! Size: " + reimbursementType);
		return reimbursementType;
	}

	@Override
	public boolean update(ReimbursementType reimbursementType) {
		logger.info("Updating reimbursement type. Reimbursement type info: " + reimbursementType);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.clear();
		
		session.update(reimbursementType);
		
		tx.commit();
		
		logger.info("Reimbursement type updated sucessfully! ");
		
		return true;
	}

	@Override
	public boolean delete(ReimbursementType reimbursementType) {
		logger.info("Deleting reimbursement type. Reimbursement type info: " + reimbursementType);
		
		Session session = HibernateUtil.getSession();
		
		Transaction tx = session.beginTransaction();
		
		session.clear();
		
		session.delete(reimbursementType);
		
		tx.commit();
		
		logger.info("Successfully deleted the reimbursement type!");
		
		return true;
	}

}
