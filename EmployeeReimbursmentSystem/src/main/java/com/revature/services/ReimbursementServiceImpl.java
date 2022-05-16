package com.revature.services;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.revature.models.Reimbursement;
import com.revature.repository.ReimbursementDAO;
import com.revature.repository.ReimbursementDAOImpl;

public class ReimbursementServiceImpl implements ReimbursementService {

	private static Logger log = Logger.getLogger(ReimbursementServiceImpl.class);
	private ReimbursementDAO reimbursementDAO;

	public ReimbursementServiceImpl(ReimbursementDAOImpl reimbursementDAOImpl) {
		super();

		this.reimbursementDAO = reimbursementDAOImpl;
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
		List<Reimbursement> reimbursements = reimbursementDAO.selectByStatus(statusId);

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

	@Override
	public List<Reimbursement> findByUserStatus(int userId, int statusId) {
		log.info("in service layer. Search Reimbursements by status: " + statusId);

		List<Reimbursement> reimbursements = reimbursementDAO.selectByUserStatus(userId, statusId);

		return reimbursements;
	}

	@Override
	public List<Reimbursement> findByUser(int userId) {
		log.info("in service layer. Search Reimbursements by user: " + userId);
		List<Reimbursement> reimbursements = reimbursementDAO.selectByUser(userId);

		return reimbursements;
	}

}
