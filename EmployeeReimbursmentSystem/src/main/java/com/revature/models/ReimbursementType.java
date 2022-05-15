package com.revature.models;

import java.util.Objects;

import javax.persistence.*;


@Entity
@Table(name = "ers_reimbursement_type")
public class ReimbursementType {
	
	@Id
	@Column(name = "reimb_type_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="reimb_type")
	private String type;

	public ReimbursementType() {
		super();
	}

	public ReimbursementType(String type) {
		super();
		this.type = type;
	}
	
	public ReimbursementType(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementType other = (ReimbursementType) obj;
		return id == other.id && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "ReimbursementType [id=" + id + ", type=" + type + "]";
	}
}
