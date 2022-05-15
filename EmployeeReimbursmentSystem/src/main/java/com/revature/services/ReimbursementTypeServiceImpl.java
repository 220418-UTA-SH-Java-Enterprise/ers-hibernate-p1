package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.ReimbursementType;
import com.revature.repository.ReimbursementTypeDAO;
import com.revature.repository.ReimbursementTypeDAOImpl;

public class ReimbursementTypeServiceImpl implements ReimbursementTypeService {
	
	private ReimbursementTypeDAO reimbursementTypeDao;
	private static Logger logger = Logger.getLogger(ReimbursementTypeServiceImpl.class);
	
	public ReimbursementTypeServiceImpl(ReimbursementTypeDAOImpl reimbursementTypeDaoImpl) {
		super();
		this.reimbursementTypeDao = reimbursementTypeDaoImpl;
	}

	@Override
	public int register(ReimbursementType reimbursementType) {
		logger.info("in serivce layer. Registering reimbursement type: " + reimbursementType);
		return reimbursementTypeDao.insert(reimbursementType);
	}

	@Override
	public ReimbursementType findReimbursementTypeById(int id) {
		logger.info("in service layer. Searching reimbursement type by id: " + id);
		return reimbursementTypeDao.selectById(id);
	}

	@Override
	public List<ReimbursementType> findAllReimbursementTypes() {
		logger.info("in service layer. finding all reimbursement types....");
		return reimbursementTypeDao.selectAll();
	}

	@Override
	public boolean editReimbursementType(ReimbursementType reimbursementType) {
		logger.info("in service layer. editing reimbursement type: " + reimbursementType);
		return reimbursementTypeDao.update(reimbursementType);
	}

	@Override
	public boolean deleteReimbursementType(int id) {
		logger.info("in service layer. deleting reimbursement type: " + id);
		return reimbursementTypeDao.delete(id);
	}
}
