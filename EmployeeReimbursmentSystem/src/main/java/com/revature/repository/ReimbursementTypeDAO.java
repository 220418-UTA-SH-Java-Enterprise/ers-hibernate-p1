package com.revature.repository;

import java.util.List;

import com.revature.models.ReimbursementType;

public interface ReimbursementTypeDAO {
	public int insert(ReimbursementType reimbursementType);
	public ReimbursementType selectById(int id);
	public List<ReimbursementType> selectAll();
	public boolean update(ReimbursementType reimbursementType);
	public boolean delete(ReimbursementType reimbursementType);
}
