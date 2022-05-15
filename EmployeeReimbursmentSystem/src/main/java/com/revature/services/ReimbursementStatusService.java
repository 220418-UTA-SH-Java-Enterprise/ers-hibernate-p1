package com.revature.services;

import java.util.List;

import com.revature.models.ReimbursementStatus;

public interface ReimbursementStatusService {
	
	public int register(ReimbursementStatus reimbursementStatus);
	
	public ReimbursementStatus findReimbursementStatusById(int id);
	
	public List<ReimbursementStatus> findAllReimbursementStatus();
	
	public boolean editReimbursementStatus(ReimbursementStatus reimbursementStatus);
	
	public boolean deleteReimbursementStatus(ReimbursementStatus reimbursementStatus);

}
