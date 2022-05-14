package com.revature.models;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "ers_reimbursement_type")
@Getter
@Setter
@EqualsAndHashCode
@ToString
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
	
}
