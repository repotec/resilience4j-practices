package com.resilience4j.bulkhead.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CurrencyExchangeController {
	
	@GetMapping("/curreny-exchange")
	@Bulkhead(name = "currenyExchange", fallbackMethod = "currenyExchangeBulkheadFullback")
	public ResponseEntity<String> currentExchange() {
		return ResponseEntity.ok().body("1.20");
	}
	
	public ResponseEntity currenyExchangeBulkheadFullback(BulkheadFullException exception) {
		HttpHeaders customHttpHeaders = new HttpHeaders();
		customHttpHeaders.set("Retry-After", "1");
		
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
				.headers(customHttpHeaders)
				.body("too many request");
	}
}
