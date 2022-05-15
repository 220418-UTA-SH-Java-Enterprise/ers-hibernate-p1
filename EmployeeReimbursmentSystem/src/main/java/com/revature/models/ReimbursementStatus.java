package com.revature.models;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "ers_reimbursement_status")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ReimbursementStatus {
	@Id // this field is primary key
	@Column(name ="reimb_status_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)//this is equivalent to the SERIAL keyword in SQL
	private int id;
	
	@Column(name ="reimb_status")
	private String status;

	public ReimbursementStatus() {   //no arg constructor
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
}
