package com.webage.api;

import java.security.Key;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.webage.domain.Customer;
import com.webage.domain.Token;
import com.webage.filter.AuthFilter;
import com.webage.repository.CustomersRepository;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/token")
public class TokenAPI {

	private Key key = AuthFilter.key;	
	
	@Autowired
	CustomersRepository repo;
	
	@GetMapping
	public String getAll() {
		return "jwt-fake-token-asdfasdfasfa".toString();
	}
	
	@PostMapping
	public ResponseEntity<?> createTokenForCustomer(@RequestBody Customer customer, HttpRequest request,
			UriComponentsBuilder uri) {
		
		String username = customer.getName();
		String password = customer.getPassword();
		
		if (username != null && username.length() > 0 && password != null && password.length() > 0 && checkPassword(username, password)) {
			Token token = createToken(username);
			ResponseEntity<?> response = ResponseEntity.ok(token);
			return response;			
		}
		// bad request
		return (ResponseEntity<?>) new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	public boolean checkPassword(String username, String password) {
		// special case for application user
		if(username.equals("ApiClientApp") && password.equals("secret")) {
			return true;
		}
		
		Iterator<Customer> customers = repo.findAll().iterator();
		while(customers.hasNext()) {
			Customer cust = customers.next();
			if(cust.getName().equals(username) && cust.getPassword().equals(password)) {
				return true;				
			}
		}
		return false;
	}
	
	
    public Token createToken(String username) {
    	String scopes = "com.webage.data.apis";
    	// special case for application user
    	if( username.equalsIgnoreCase("ApiClientApp")) {
    		scopes = "com.webage.auth.apis";
    	}
    	long fiveHoursInMillis = 1000 * 60 *60 * 5;
    	
    	String token_string = Jwts.builder()
    			.setSubject("Joe")
    			.setIssuer("me@me.com")
    			.claim("scopes",scopes)
    			.setExpiration(new Date(System.currentTimeMillis() + fiveHoursInMillis))
    			.signWith(key)
    			.compact(); 
    	return new Token(token_string);
    }
}
