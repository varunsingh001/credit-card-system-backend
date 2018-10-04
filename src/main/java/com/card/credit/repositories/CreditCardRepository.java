package com.card.credit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.card.credit.beans.CreditCard;
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, String> { 
	public CreditCard findByCardNumber(String cardNumber);	
}