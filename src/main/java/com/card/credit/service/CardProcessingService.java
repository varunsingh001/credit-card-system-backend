package com.card.credit.service;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.credit.beans.CardAdditionReply;
import com.card.credit.beans.CreditCard;
import com.card.credit.exceptions.ValidationException;
import com.card.credit.repositories.CreditCardRepository;
import com.card.credit.util.Validator;

@Service
public class CardProcessingService {

	private final CreditCardRepository repository;

	@Autowired
	public CardProcessingService(CreditCardRepository repository) {
		this.repository = repository;
	}

	public CardAdditionReply addCreditCard(CreditCard card) {
		final CardAdditionReply reply = new CardAdditionReply();
		if (card != null) {
			final CreditCard existingCard = repository.findByCardNumber(card.getCardNumber());
			if (existingCard == null) {
				validateAndAdd(card, reply);
			}else {
				throw new ValidationException("Card exists !");
			}
		}
		return reply;
	}

	public List<CreditCard> getAllCreditCards() {
		return repository.findAll();
	}

	private void validateAndAdd(CreditCard card, final CardAdditionReply reply) {
		if (!Validator.luhnValidator(card.getCardNumber())) {
			throw new ValidationException("Card number failed Luhn check");
		}
		repository.save(card);
		reply.setMessage("operation successful");
		reply.setResponseCode(CREATED);
	}

}
