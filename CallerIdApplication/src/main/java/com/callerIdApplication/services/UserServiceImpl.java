package com.callerIdApplication.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callerIdApplication.entity.Contact;
import com.callerIdApplication.entity.CurrentUserSession;
import com.callerIdApplication.entity.Spam;
import com.callerIdApplication.entity.User;
import com.callerIdApplication.exceptions.UserException;
import com.callerIdApplication.repostitory.ContactDao;
import com.callerIdApplication.repostitory.SessionDao;
import com.callerIdApplication.repostitory.SpamDao;
import com.callerIdApplication.repostitory.UserDao;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao cDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private ContactDao contactDao;
	
	@Autowired
	private SpamDao spamDao;
	
	
	@Override
	public String createCustomer(User customer)throws UserException {
		
		
		User existingCustomer= cDao.findByphoneNumber(customer.getPhoneNumber());		
		System.out.println(existingCustomer);
		
		if(existingCustomer != null) 
			throw new UserException("Customer Already Registered with Mobile number");
			
		
		cDao.save(customer);
		
			return "User Registeration Success";
			
			
		}




	@Override
	public List<Contact> addContact(Contact contact, String key) throws UserException {
		CurrentUserSession currentUserSession= sessionDao.findByUuid(key);
		if(currentUserSession==null)
			throw new UserException("Login First");
		
		Optional<User> optional =cDao.findById(currentUserSession.getUserId());
		
		User user=optional.get();
		
		user.getContacts().add(contact);
		contact.setUser(user);
		cDao.save(user);
		
		return user.getContacts();
		
	}




	@Override
	public List<?> searchContact(String name, String key) throws UserException {
		CurrentUserSession currentUserSession= sessionDao.findByUuid(key);
		if(currentUserSession==null)
			throw new UserException("Login First");
//		return contactDao.getContactbykeywords(name);
		User user= cDao.findByuserName(name);
		if(user==null)
		{
			List<Contact> contacts= contactDao.getContactbykeywords(name);
			if(contacts.size()==0)
				throw new UserException("Data not found");
			
			List<Spam> spams= spamDao.findByname(name);
			if(spams.size()==0)
			{
				for(Contact c:contacts)
				{
					
							Spam spam=new Spam();
							
							spam.setName(c.getName());
							spam.setNumber(c.getNumber());
							spam.setSpammer(false);
							spams.add(spam);
				}
				return spams;
			}
				
			
			List<Spam> result=new ArrayList<>();
			for(Contact c:contacts)
			{
				for(Spam s:spams)
				{
					if(c.getNumber().equals(s.getNumber()) && c.getName().equals(s.getName()))
					{
						Spam spam=new Spam();
						spam.setSpamID(s.getSpamID());
						spam.setName(c.getName());
						spam.setNumber(c.getNumber());
						spam.setSpammer(true);
						result.add(spam);
					}
					else {
						Spam spam=new Spam();
						spam.setName(c.getName());
						spam.setNumber(c.getNumber());
						spam.setSpammer(false);
						result.add(spam);
					}
				}
			}
			
			
			
			
			return result;
			
		}
		else {
			
			List<Spam> sOptional= spamDao.findByname(user.getUserName());
			
			if(sOptional.size()>0)
			{
				List<Spam> spams=new ArrayList<>();
				spams.add(sOptional.get(0));
				return spams;
			}
			List<Spam> list=new ArrayList<>();
			Spam spam=new Spam();
			spam.setName(user.getUserName());
			spam.setNumber(user.getPhoneNumber());
			spam.setSpammer(false);
			
			list.add(spam);
			return list;
		}
	}




	@Override
	public List<?> searchPersonByNumber(String Number,String key) throws UserException {
		CurrentUserSession currentUserSession= sessionDao.findByUuid(key);
		if(currentUserSession==null)
			throw new UserException("Login First");

		Optional<User> existingUserOptional=cDao.findById(currentUserSession.getUserId());
		
		User user= cDao.findByphoneNumber(Number);
		if(user==null)
		{
			List<Contact> contacts= contactDao.getAllContactByphoneNumber(Number);
			List<Spam> result=new ArrayList<>();
			if(contacts.size()==0)
			{
				Spam spam=new Spam();
				spam.setName("Unknown");
				spam.setNumber(Number);
				spam.setSpammer(true);
				Spam spam2= spamDao.save(spam);
				 result.add(spam2);
				 return result;
			}
			
			List<Spam> spams= spamDao.findBynumber(Number);
			if(spams.size()==0)
			{
				for(Contact c:contacts)
				{
					Spam spam=new Spam();
					spam.setName(c.getName());
					spam.setNumber(c.getNumber());
					spam.setSpammer(false);
					spams.add(spam);
				}
				
				return spams;
			}
				
			
			
			for(Contact c:contacts)
			{
				for(Spam s:spams)
				{
					if(c.getNumber().equals(s.getNumber()) && c.getName().equals(s.getName()))
					{
						Spam spam=new Spam();
						spam.setSpamID(s.getSpamID());
						spam.setName(c.getName());
						spam.setNumber(c.getNumber());
						spam.setSpammer(true);
						result.add(spam);
					}
					else {
						Spam spam=new Spam();
						spam.setName(c.getName());
						spam.setNumber(c.getNumber());
						spam.setSpammer(false);
						result.add(spam);
					}
				}
			}
			
			
			
			
			return result;
			
		}
		else {
			
			Optional<Spam> sOptional= spamDao.findById(user.getUserId());
			
			if(sOptional.isPresent())
			{
				List<Spam> spams=new ArrayList<>();
				spams.add(sOptional.get());
				return spams;
			}
			
			if(user.getUserId().equals(existingUserOptional.get().getUserId()))
			{
				List<String> strings=new ArrayList<>();
				String gmailString=existingUserOptional.get().getEmail();
				strings.add(gmailString);
				return strings;
			}
			List<Spam> list=new ArrayList<>();
			
			Spam spam=new Spam();
			spam.setName(user.getUserName());
			spam.setNumber(user.getPhoneNumber());
			spam.setSpammer(false);
			list.add(spam);
			return list;
		}
		
		
	}

		
		
	}


