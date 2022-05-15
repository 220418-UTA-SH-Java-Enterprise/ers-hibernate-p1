package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.ReimbursementStatus;
import com.revature.repository.ReimbursementStatusDAO;
import com.revature.repository.ReimbursementStatusDAOImpl;

public class ReimbursementStatusServiceImpl implements ReimbursementStatusService{
	
	private ReimbursementStatusDAO reimbursementStatusDao;
	private static Logger logger = Logger.getLogger(ReimbursementStatusServiceImpl.class);
	
	public ReimbursementStatusServiceImpl(ReimbursementStatusDAOImpl reimbursementStatusDaoImpl) {
		super();
		this.reimbursementStatusDao = reimbursementStatusDaoImpl;
	}

	@Override
	public int register(ReimbursementStatus reimbursementStatus) {
		logger.info("in serivce layer. Registering reimbursement status: " + reimbursementStatus);
		return reimbursementStatusDao.insert(reimbursementStatus);
	}

	@Override
	public ReimbursementStatus findReimbursementStatusById(int id) {
		logger.info("in service layer. Searching reimbursement status by id: " + id);
		return reimbursementStatusDao.selectById(id);
	}

	@Override
	public List<ReimbursementStatus> findAllReimbursementStatus() {
		logger.info("in service layer. Finding all reimbursement status....");
		return reimbursementStatusDao.selectAll();
	}

	@Override
	public boolean editReimbursementStatus(ReimbursementStatus reimbursementStatus) {
		logger.info("in service layer. Editing reimbursement status: " + reimbursementStatus);
		return reimbursementStatusDao.update(reimbursementStatus);
	}

	@Override
	public boolean deleteReimbursementStatus(int id) {
		logger.info("in service layer. Deleting reimbursement status: " + id);
		return reimbursementStatusDao.delete(id);
	}
}
