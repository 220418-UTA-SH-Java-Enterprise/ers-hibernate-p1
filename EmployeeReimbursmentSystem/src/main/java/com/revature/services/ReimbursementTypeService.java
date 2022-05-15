package com.revature.services;

import java.util.List;

import com.revature.models.ReimbursementType;

public interface ReimbursementTypeService {
	
	public int register(ReimbursementType reimbursementType);
	
	public ReimbursementType findReimbursementTypeById(int id);
	
	public List<ReimbursementType> findAllReimbursementTypes();
	
	public boolean editReimbursementType(ReimbursementType reimbursementType);
	
	public boolean deleteReimbursementType(ReimbursementType reimbursementType);
}
