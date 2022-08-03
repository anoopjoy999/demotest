package com.example.demotest.service;

import com.example.demotest.entity.NaceOrder;
import com.example.demotest.exceptions.NaceException;
import com.example.demotest.model.NaceOrderDto;

public interface NaceService {
	
	
	public NaceOrder putNaceDetails(NaceOrderDto user) throws NaceException;
	public NaceOrderDto getNaceDetails(String order) throws NaceException;

}
