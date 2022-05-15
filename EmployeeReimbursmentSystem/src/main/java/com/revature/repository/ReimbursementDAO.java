package com.revature.repository;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
	public int insert(Reimbursement reimbursement);
	public Reimbursement selectById(int id);
	public List<Reimbursement> selectAll();
	public boolean update(Reimbursement reimbursement);
	public boolean delete(int id);
}
