package com.card.credit.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.card.credit.beans.CardAdditionReply;
import com.card.credit.beans.CreditCard;
import com.card.credit.service.CardProcessingService;

@Controller
public class CreditCardControllers {

	private final CardProcessingService cardService;
	@Autowired
	public CreditCardControllers(final CardProcessingService cardService) {
		this.cardService = cardService;
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/api/v1/add")
    @ResponseBody
    public ResponseEntity<CardAdditionReply> addCard(@Valid @RequestBody final CreditCard card) {
		final CardAdditionReply reply =  cardService.addCreditCard(card);
		final ResponseEntity<CardAdditionReply> responseEntity = new ResponseEntity<>(reply, reply.getResponseCode());
		return responseEntity;
    }
	
	@CrossOrigin(origins = "*")
	@GetMapping("/api/v1/getAll")
    @ResponseBody
    public List<CreditCard> getAll() {
        return cardService.getAllCreditCards();
    }
}
