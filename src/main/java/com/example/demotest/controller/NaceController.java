package com.example.demotest.controller;


import com.example.demotest.config.ExceptionMsgs;
import com.example.demotest.config.RequestMappingConfig;
import com.example.demotest.exceptions.ExceptionResponse;
import com.example.demotest.exceptions.NaceException;
import com.example.demotest.model.NaceOrderDto;
import com.example.demotest.model.ResponseMessage;
import com.example.demotest.service.NaceService;
import com.example.demotest.utils.DataUtils;
import com.example.demotest.utils.Log4jManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class NaceController {
	
	
	@Autowired
	private NaceService naceService;
	
	@PostMapping(RequestMappingConfig.PUT_NACE_DETAILS)
	public ResponseMessage putNaceDetails(@RequestBody NaceOrderDto order) throws NaceException{
		String message = null;
		try {
			naceService.putNaceDetails(order);
			message = "Nace details added successfully: " + order.getOrder();
			return new ResponseMessage(message);
		} catch(NaceException e) {
    		throw e;
    	}
    	catch(Exception e) {
    		Log4jManager.writeErrorLog("Exception in NaceController.putNaceDetails");
    		ExceptionResponse exceptionResponse = new ExceptionResponse();
    		DataUtils.setExceptionResponses(ExceptionMsgs.GENERAL_EXCEPTION,exceptionResponse);
    		DataUtils.setExceptionTrace(e,exceptionResponse);
    		throw new NaceException(exceptionResponse);
    	}
	}
	
	
	@GetMapping(RequestMappingConfig.GET_NACE_DETAILS)
	public NaceOrderDto getNaceDetails(@RequestParam String order) throws NaceException{
		try {
			return naceService.getNaceDetails(order);
			
		} catch(NaceException e) {
    		throw e;
    	}
    	catch(Exception e) {
    		Log4jManager.writeErrorLog("Exception in NaceController.getNaceDetails");
    		ExceptionResponse exceptionResponse = new ExceptionResponse();
    		DataUtils.setExceptionResponses(ExceptionMsgs.GENERAL_EXCEPTION,exceptionResponse);
    		DataUtils.setExceptionTrace(e,exceptionResponse);
    		throw new NaceException(exceptionResponse);
    	}
	}

}
