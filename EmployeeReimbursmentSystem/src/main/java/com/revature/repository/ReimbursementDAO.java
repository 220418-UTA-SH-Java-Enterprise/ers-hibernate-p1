package com.revature.repository;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
	public int insert(Reimbursement reimbursement);
	public Reimbursement selectById(int id);
	public List<Reimbursement> selectAll();
	public List<Reimbursement> selectByStatus(int statusId);
	public List<Reimbursement> selectByUserStatus(int userId, int statusId);
	public List<Reimbursement> selectByUser(int userId);
	public boolean update(Reimbursement reimbursement);
	public boolean delete(int id);
}
