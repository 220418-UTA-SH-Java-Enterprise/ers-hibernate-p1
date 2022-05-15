package com.revature.models;

import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "ers_reimbursement_status")
public class ReimbursementStatus {
	@Id // this field is primary key
	@Column(name ="reimb_status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)//this is equivalent to the SERIAL keyword in SQL
	private int id;
	
	@Column(name ="reimb_status")
	private String status;

	public ReimbursementStatus() {  
		super();
	}

	public ReimbursementStatus(String status) {
		super();
		this.status = status;
	}

	public ReimbursementStatus(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementStatus other = (ReimbursementStatus) obj;
		return id == other.id && Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "ReimbursementStatus [id=" + id + ", status=" + status + "]";
	}
	
	
}
