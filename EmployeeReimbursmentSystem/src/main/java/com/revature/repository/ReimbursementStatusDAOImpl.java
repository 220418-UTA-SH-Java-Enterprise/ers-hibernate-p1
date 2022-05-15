package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.ReimbursementStatus;
import com.revature.util.HibernateUtil;

public class ReimbursementStatusDAOImpl implements ReimbursementStatusDAO{
	
	private static Logger logger = Logger.getLogger(ReimbursementStatusDAOImpl.class);

	@Override
	public int insert(ReimbursementStatus reimbursementStatus) {
		logger.info("Adding a new reimbursment status to database. reimbursement status info" + reimbursementStatus);
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		int reimbursementId = (int) session.save(reimbursementStatus);
		
		tx.commit();
		
		logger.info("insert successful! New Reimbursement id is " + reimbursementId);
		return reimbursementId; // return the auto-generated reimbursementStatusId
	}

	@Override
	public ReimbursementStatus sellectById(int id) {
		logger.info("searching reimbursement status by id: " + id);
		
		Session session = HibernateUtil.getSession();
		
		IdentifierLoadAccess<ReimbursementStatus> identifierObj = session.byId(ReimbursementStatus.class);
		ReimbursementStatus reimbursementStatus = identifierObj.load(id);
		
		logger.info("Search compleete! Found: " + reimbursementStatus);
		
		return reimbursementStatus;
	}

	@Override
	public List<ReimbursementStatus> selectAll() {
		//TODO: please use plural in log entries and variables names
		logger.info("Getting all reimbursement status from database....");
		
		Session session  = HibernateUtil.getSession();
		
		List<ReimbursementStatus> reimbursementStatus = session.createQuery("from Reimbursement", ReimbursementStatus.class).list();
		//TODO: size method is missing
		logger.info("Reimbursement status list retireved! Size: " + reimbursementStatus);
		return reimbursementStatus;
	}

	@Override
	public boolean update(ReimbursementStatus reimbursementStatus) {
		logger.info("Updating reimbursement status. Reimbursement status info: " + reimbursementStatus);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		session.clear();
		
		session.update(reimbursementStatus);
		
		tx.commit();
		
		logger.info("Reimbursement status updated sucessfully! ");
		return true;
	}

	@Override
	public boolean delete(int id) {
		ReimbursementStatus reimbursementStatus = sellectById(id);
		
		if (reimbursementStatus != null) {
			logger.info("Deleting reimbursement status. Reimbursement status info: " + reimbursementStatus);
			
			Session session = HibernateUtil.getSession();
			
			Transaction tx = session.beginTransaction();
			
			session.clear();
			
			session.delete(reimbursementStatus);
			
			tx.commit();
			
			logger.info("Successfully deleted the reimbursement status!");
			
			return true;
		} else {
			logger.info("Delete failed: Unable to find a reimbursement status with id: " + id);
			return false;	
		}
		
	}

}
