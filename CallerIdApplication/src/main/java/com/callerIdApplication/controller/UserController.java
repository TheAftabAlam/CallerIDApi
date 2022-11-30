package com.callerIdApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callerIdApplication.entity.Contact;
import com.callerIdApplication.entity.User;
import com.callerIdApplication.exceptions.UserException;
import com.callerIdApplication.services.UserService;



@RestController

public class UserController {

	@Autowired
	private UserService cService;
	
	
	
	
	@PostMapping("/addUser")
	public ResponseEntity<String> saveUser(@RequestBody User user) throws UserException {
		
		
		
		
		String savedUser= cService.createCustomer(user);
		
		
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}
	@PostMapping("/user/addContact")
	public ResponseEntity<List<Contact>> saveContact(@RequestBody Contact contact, @RequestParam(required = false) String key) throws UserException {
		
		List<Contact> contacts= cService.addContact(contact, key);
		
		
		return new ResponseEntity<>(contacts,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/search/{name}")
public ResponseEntity<List<?>> searchContact(@PathVariable("name") String name, @RequestParam(required = false) String key) throws UserException {
		
		List<?> contacts= cService.searchContact(name, key);
		
		
		return new ResponseEntity<>(contacts,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/searchPerson/number={num}")
	public ResponseEntity<List<?>> searchPersonByPhoneNumber(@PathVariable("num") String num, @RequestParam(required = false) String key) throws UserException {
			
			List<?> contacts= cService.searchPersonByNumber(num, key);
			
			
			return new ResponseEntity<>(contacts,HttpStatus.CREATED);
		}
	
	
	
}
