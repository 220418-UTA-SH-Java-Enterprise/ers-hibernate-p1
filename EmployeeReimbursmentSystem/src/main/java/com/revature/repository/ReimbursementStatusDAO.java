package com.revature.repository;

import java.util.List;

import com.revature.models.ReimbursementStatus;

public interface ReimbursementStatusDAO {
	public int insert(ReimbursementStatus reimbursementStatus);
	public ReimbursementStatus sellectById(int id);
	public List<ReimbursementStatus> selectAll();
	public boolean update(ReimbursementStatus reimbursementStatus);
	public boolean delete(int id);
}
