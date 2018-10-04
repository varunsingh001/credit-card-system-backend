package com.card.credit.beans;

import static javax.persistence.GenerationType.AUTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class CreditCard {
	@Id
	@GeneratedValue(strategy = AUTO)
	private Long id;
	@NotEmpty(message = "Card Number cannot be null")
	@NotNull
	private String cardNumber;
	@NotEmpty
	@NotNull
	private String name;
	private Double amount = 0.0;
	@NotNull
	private Double accountLimit;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAccountLimit() {
		return accountLimit;
	}
	public void setAccountLimit(Double accountLimit) {
		this.accountLimit = accountLimit;
	}
	
}
