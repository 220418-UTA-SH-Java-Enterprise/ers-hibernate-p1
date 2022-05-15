package com.revature.models;

import java.time.LocalDate;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "ers_reimbursement")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Reimbursement {

	@Id
	@Column(name="reimb_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="reimb_amount")
	private double amount;
	
	@Column(name="reimb_submitted")
	private LocalDate submittedDate;

	@Column(name="reimb_resolved")
	private LocalDate resolvedDate;
	
	@Column(name="reimb_description")
	private String description;
	
	@Column(name="reimb_receipt")
	private byte[] receipt;
	
	@ManyToOne
    @JoinColumn(name ="reimb_author", referencedColumnName = "ers_user_id")
	private User Author;
	
	@ManyToOne
    @JoinColumn(name ="reimb_resolver", referencedColumnName = "ers_user_id")
	private User Resolver;
	
	@ManyToOne
    @JoinColumn(name ="reimb_status_id", referencedColumnName = "reimb_status_id")
	private ReimbursementStatus status;
	
	@ManyToOne
    @JoinColumn(name ="reimb_type_id", referencedColumnName = "reimb_type_id")
	private ReimbursementType type;

	public Reimbursement() {
		super();
	}

	public Reimbursement(double amount, LocalDate submittedDate, LocalDate resolvedDate, String description, byte[] receipt,
			User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.amount = amount;
		this.submittedDate = submittedDate;
		this.resolvedDate = resolvedDate;
		this.description = description;
		this.receipt = receipt;
		Author = author;
		Resolver = resolver;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(int id, double amount, LocalDate submittedDate, LocalDate resolvedDate, String description,
			byte[] receipt, User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submittedDate = submittedDate;
		this.resolvedDate = resolvedDate;
		this.description = description;
		this.receipt = receipt;
		Author = author;
		Resolver = resolver;
		this.status = status;
		this.type = type;
	}
}
