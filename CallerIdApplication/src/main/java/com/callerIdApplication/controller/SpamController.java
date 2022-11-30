package com.callerIdApplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callerIdApplication.exceptions.UserException;
import com.callerIdApplication.services.SpamService;


@RestController
@RequestMapping("/spam")
public class SpamController {
	@Autowired
	private SpamService service;
	@PostMapping("/add/{number}")
	public ResponseEntity<String> markAsSpamHandler(@PathVariable("number") String number,@RequestParam(required = false) String key) throws UserException
	{
		String spam= service.markAsSpammer(number, key);
		
		return new ResponseEntity<>(spam,HttpStatus.OK);
	}

}
