package com.callerIdApplication.services;
import com.callerIdApplication.exceptions.UserException;

public interface SpamService {
	
	

	public String markAsSpammer(String number, String key) throws UserException;
	

}
