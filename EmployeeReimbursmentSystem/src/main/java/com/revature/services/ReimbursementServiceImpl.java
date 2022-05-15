package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.User;
import com.revature.repository.ReimbursementDAO;
import com.revature.repository.ReimbursementDAOImpl;
import com.revature.repository.ReimbursementStatusDAO;
import com.revature.repository.ReimbursementStatusDAOImpl;
import com.revature.repository.UserDAO;
import com.revature.repository.UserDAOImpl;

public class ReimbursementServiceImpl implements ReimbursementService {
	
	private static Logger log = Logger.getLogger(ReimbursementServiceImpl.class);
	private ReimbursementDAO reimbursementDAO;
	private ReimbursementStatusDAO statusDao;
	private UserDAO userDao;
	
	public ReimbursementServiceImpl(ReimbursementDAOImpl reimbursementDAOImpl, 
			ReimbursementStatusDAOImpl reimbursementStatusDaoImpl,
			UserDAOImpl userDaoImpl) {
		super();
		
		this.reimbursementDAO = reimbursementDAOImpl;
		this.statusDao = reimbursementStatusDaoImpl;
		this.userDao = userDaoImpl;
	}

	@Override
	public int submit(Reimbursement reimbursement) {
		log.info("in service layer. Registering user: " + reimbursement);
		return reimbursementDAO.insert(reimbursement);
	}

	@Override
	public Reimbursement findById(int id) {
		log.info("in service layer. searching reimbursement by id: " + id);
		return reimbursementDAO.selectById(id);
	}

	@Override
	public List<Reimbursement> findByStatus(int statusId) {
		log.info("in service layer. Search Reimbursements by status: " + statusId);
		ReimbursementStatus status = statusDao.selectById(statusId);
		
		List<Reimbursement> reimbursements = reimbursementDAO.selectAll()
				.stream()
				.filter(r -> (r.getStatus().equals(status)))
				.toList();

		return reimbursements;
	}

	@Override
	public List<Reimbursement> findByStatusAndUser(int statusId, int userId) {
		log.info("in service layer. Search Reimbursements by status: " + statusId);
		ReimbursementStatus status = statusDao.selectById(statusId);
		User employee = userDao.selectById(userId);
		
		List<Reimbursement> reimbursements = reimbursementDAO.selectAll()
				.stream()
				.filter(r -> (r.getStatus().equals(status) && r.getAuthor().equals(employee)))
				.toList();

		return reimbursements;
	}

	@Override
	public List<Reimbursement> findAll() {
		log.info("in service layer. finding all reimbursements");
		return reimbursementDAO.selectAll();
	}

	@Override
	public boolean edit(Reimbursement reimbursement) {
		log.info("in service layer. editing reimbursement: " + reimbursement);
		return reimbursementDAO.update(reimbursement);
	}

	@Override
	public boolean delete(int id) {
		log.info("in service layer. deleting reimbursement by id: " + id);
		return reimbursementDAO.delete(id);
	}

}
