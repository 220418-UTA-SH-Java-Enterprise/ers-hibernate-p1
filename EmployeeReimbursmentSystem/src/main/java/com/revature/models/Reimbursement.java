package com.revature.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "ers_reimbursement")
public class Reimbursement {

	@Id
	@Column(name="reimb_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="reimb_amount", nullable = false)
	private double amount;
	
	@Column(name="reimb_submitted", nullable = false)
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(LocalDate submittedDate) {
		this.submittedDate = submittedDate;
	}

	public LocalDate getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(LocalDate resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public User getAuthor() {
		return Author;
	}

	public void setAuthor(User author) {
		Author = author;
	}

	public User getResolver() {
		return Resolver;
	}

	public void setResolver(User resolver) {
		Resolver = resolver;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public ReimbursementType getType() {
		return type;
	}

	public void setType(ReimbursementType type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result
				+ Objects.hash(Author, Resolver, amount, description, id, resolvedDate, status, submittedDate, type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Objects.equals(Author, other.Author) && Objects.equals(Resolver, other.Resolver)
				&& Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(description, other.description) && id == other.id
				&& Arrays.equals(receipt, other.receipt) && Objects.equals(resolvedDate, other.resolvedDate)
				&& Objects.equals(status, other.status) && Objects.equals(submittedDate, other.submittedDate)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submittedDate=" + submittedDate + ", resolvedDate="
				+ resolvedDate + ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", Author="
				+ Author + ", Resolver=" + Resolver + ", status=" + status + ", type=" + type + "]";
	}
	
	
	
}
