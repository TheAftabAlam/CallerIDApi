package com.callerIdApplication.services;

import java.util.List;

import com.callerIdApplication.entity.Contact;
import com.callerIdApplication.entity.User;
import com.callerIdApplication.exceptions.UserException;

public interface UserService {
	
	
	public String createCustomer(User user)throws UserException;
	public List<Contact> addContact(Contact contact,String key) throws UserException;
	public List<?> searchContact(String name,String key) throws UserException;
	
	public List<?> searchPersonByNumber(String Number,String key) throws UserException;
	
	
	
}
