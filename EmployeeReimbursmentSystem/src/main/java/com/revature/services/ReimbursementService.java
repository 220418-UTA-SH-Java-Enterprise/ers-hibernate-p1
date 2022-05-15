package com.revature.services;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementService {

	public int submit(Reimbursement reimbursement);

	public Reimbursement findById(int id);
	
	public List<Reimbursement> findByStatus(int statusId);
	
	public List<Reimbursement> findByStatusAndUser(int statusId, int userId);

	public List<Reimbursement> findAll();

	public boolean edit(Reimbursement reimbursement);

	public boolean delete(int id);
}
