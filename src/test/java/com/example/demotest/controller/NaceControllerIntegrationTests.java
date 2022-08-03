package com.example.demotest.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import com.example.demotest.DemotestApplication;
import com.example.demotest.model.NaceOrderDto;
import com.example.demotest.model.ResponseMessage;



@SpringBootTest(classes = DemotestApplication.class,
		webEnvironment = WebEnvironment.RANDOM_PORT)
public class NaceControllerIntegrationTests 
{
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Sql({ "classpath:schema.sql", "classpath:data.sql" })
	@Test
	public void testGetNaceDetails() 
	{
		assertTrue(this.restTemplate
					.getForObject("http://localhost:" + port + "/getnacedetails?order=1", NaceOrderDto.class)
					.getOrder()!=null);
	}

	@Test
	public void testPutNaceDetails() {
		NaceOrderDto dto = new NaceOrderDto("one","two","three","four","five","six","seven","eight","nine","10");
		ResponseMessage responseEntity = this.restTemplate
			.postForObject("http://localhost:" + port + "/putnacedetails", dto, ResponseMessage.class);
		assertTrue(responseEntity.getMessage()!=null);
	}
}
