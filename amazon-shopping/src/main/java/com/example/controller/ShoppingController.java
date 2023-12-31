package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.serviceclients.PaymentServiceClient;
import com.example.serviceclients.MessagingServiceClient;

/**
 * @author Samarth Narula
 *
 */

@RestController
public class ShoppingController {

	@Autowired
	PaymentServiceClient paymentClient;
	
	@Autowired
	MessagingServiceClient messageClient;

	@GetMapping("/amazon-payment/{price}")
	public String invokePaymentService(@PathVariable int price) {
		messageClient.publishMessage("Your payment for " + price + " INR has been instantiated", buildEmailBody(price));
		return paymentClient.invokePaymentService(price);
	}

	private String buildEmailBody(int price) {
		return java.lang.String.format("Dear user ,\n\n\nYour payment process for amount %d has been instantiated.\n\nThanks & Regards,\nxyz company", price);
	}

}