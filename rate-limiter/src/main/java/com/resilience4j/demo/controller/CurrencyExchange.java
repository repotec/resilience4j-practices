package com.resilience4j.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CurrencyExchange {
	
	@GetMapping("/curreny-exchange")
	@RateLimiter(name = "currentExchange", fallbackMethod = "currentExchangeFullback")
	public ResponseEntity<String> getCurrentExchange() {
		return ResponseEntity.ok().body("1.34");
	}
	
	
	public ResponseEntity currentExchangeFullback(RequestNotPermitted exception) {
		
		HttpHeaders customHttpHeaders = new HttpHeaders();
		customHttpHeaders.set("retry-after", "1");
		
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
				.headers(customHttpHeaders)
				.body("too many request");
	}
}
