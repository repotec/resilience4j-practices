package com.resilience4j.ratelimiter.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CurrencyExchangeController {
	
	@GetMapping("/curreny-exchange")
	@RateLimiter(name = "currenyExchange", fallbackMethod = "currenyExchangeRatelimiterFullback")
	public ResponseEntity<String> currentExchange() {
		return ResponseEntity.ok().body("1.34");
	}
	
	public ResponseEntity currenyExchangeRatelimiterFullback(RequestNotPermitted exception) {
		HttpHeaders customHttpHeaders = new HttpHeaders();
		customHttpHeaders.set("Retry-After", "1");
		
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
				.headers(customHttpHeaders)
				.body("too many request");
	}
}
