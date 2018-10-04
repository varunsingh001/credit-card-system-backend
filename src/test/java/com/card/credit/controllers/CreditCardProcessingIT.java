package com.card.credit.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.card.credit.CardProcessingApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CardProcessingApplication.class)
@WebAppConfiguration
public class CreditCardProcessingIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private CreditCardControllers controller;
	

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

	@Test
	public void addCardTest() throws Exception {		
		mockMvc.perform(put("/api/v1/add")
				.contentType(APPLICATION_JSON_UTF8)
                .content("{\r\n" +                 	
                		 "   \"cardNumber\": \"1234567812345670\",\r\n" + 
                		 "   \"name\": \"Test\",\r\n" + 
                		 "   \"amount\": 0,\r\n" + 
                		 "   \"accountLimit\": 10000\r\n" + 
                		 "}"))
                .andExpect(status().isCreated());
	}

	@Test
	public void addCardWithInValidCardNumberTest() throws Exception {		
		mockMvc.perform(put("/api/v1/add")
				.contentType(APPLICATION_JSON_UTF8)
                .content("{\r\n" +                 	
                		 "   \"cardNumber\": \"1\",\r\n" + 
                		 "   \"name\": \"Test\",\r\n" + 
                		 "   \"amount\": 0,\r\n" + 
                		 "   \"accountLimit\": 10000\r\n" + 
                		 "}"))
                .andExpect(status().isBadRequest());
	}
	
	@Test
	public void addCardWithNoNameTest() throws Exception {		
		mockMvc.perform(put("/api/v1/add")
				.contentType(APPLICATION_JSON_UTF8)
                .content("{\r\n" +                 	
                		 "   \"cardNumber\": \"1234567812345670\",\r\n" + 
                		 "   \"amount\": 0,\r\n" + 
                		 "   \"accountLimit\": 10000\r\n" + 
                		 "}"))
                .andExpect(status().isBadRequest());
	}	
	
	@Test
	public void addCardWithNoAmountTest() throws Exception {		
		mockMvc.perform(put("/api/v1/add")
				.contentType(APPLICATION_JSON_UTF8)
                .content("{\r\n" +                 	
               		 "   \"cardNumber\": \"1234567812345670\",\r\n" + 
               		 "   \"name\": \"Test\",\r\n" + 
               		 "   \"accountLimit\": 10000\r\n" + 
               		 "}"))
                .andExpect(status().isCreated());
		Assert.assertTrue(controller.getAll().size() == 1);
		Assert.assertTrue(controller.getAll().get(0).getAmount() == 0);
	}
	
	@Test
	public void addCardWithContentTypeWrongTest() throws Exception {		
		mockMvc.perform(put("/api/v1/add")
				.contentType(TEXT_PLAIN)
                .content("{\r\n" +                 	
               		 "   \"cardNumber\": \"1234567812345670\",\r\n" + 
               		 "   \"name\": \"Test\",\r\n" + 
               		 "   \"amount\": 0,\r\n" + 
               		 "   \"accountLimit\": 10000\r\n" + 
               		 "}"))
                .andExpect(status().isUnsupportedMediaType());
	}
	
	@Test
	public void addCardWithAddAndGetAllTest() throws Exception {		
		mockMvc.perform(put("/api/v1/add")
				.contentType(APPLICATION_JSON_UTF8)
                .content("{\r\n" +                 	
               		 "   \"cardNumber\": \"1234567812345670\",\r\n" + 
               		 "   \"name\": \"Test\",\r\n" + 
               		 "   \"accountLimit\": 10000\r\n" + 
               		 "}"))
                .andExpect(status().isCreated());
		
		mockMvc.perform(get("/api/v1/getAll")
			      .contentType(APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("Test")))
			      .andExpect(jsonPath("$[0].cardNumber", is("1234567812345670")))
			      .andExpect(jsonPath("$[0].accountLimit", is(10000.0)));
	}
	
	@Test
	public void addCardWithAddSameAgainTest() throws Exception {		
		mockMvc.perform(put("/api/v1/add")
				.contentType(APPLICATION_JSON_UTF8)
                .content("{\r\n" +                 	
               		 "   \"cardNumber\": \"1234567812345670\",\r\n" + 
               		 "   \"name\": \"Test\",\r\n" + 
               		 "   \"accountLimit\": 10000\r\n" + 
               		 "}"))
                .andExpect(status().isCreated());
		
		mockMvc.perform(put("/api/v1/add")
				.contentType(APPLICATION_JSON_UTF8)
                .content("{\r\n" +                 	
               		 "   \"cardNumber\": \"1234567812345670\",\r\n" + 
               		 "   \"name\": \"Test\",\r\n" + 
               		 "   \"accountLimit\": 100\r\n" + 
               		 "}"))
                .andExpect(status().isBadRequest());
				
	}

}
