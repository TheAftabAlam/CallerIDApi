package com.callerIdApplication.services;

import java.util.ArrayList;
import java.util.List;

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
public class SpamServiceimpl implements SpamService{
	
		@Autowired
		private UserDao userDao;
		
		@Autowired
		private ContactDao contactDao;

		@Autowired
		private SessionDao sessionDao;
		
		@Autowired
		private SpamDao spamDao;
		
		
		@Override
		public String markAsSpammer(String number, String key) throws UserException {
			CurrentUserSession currentUserSession= sessionDao.findByUuid(key);
			if(currentUserSession==null)
				throw new UserException("Login First");
			
			List<Spam> spams=new ArrayList<>();
			Spam spam=new Spam();
			User user= userDao.findByphoneNumber(key);
			if(user!=null)
			{
			
				
				spam.setName(user.getUserName());
				spam.setNumber(user.getPhoneNumber());
				spam.setSpammer(true);
				Spam spam2=spamDao.save(spam);
				spams.add(spam2);
				return "Marked as Spammer!";
			}
			
			
			List<Contact> contacts= contactDao.getAllContactByphoneNumber(number);
			
			
			if(contacts.size()>0)
			{
			
				for(Contact c:contacts)
				{

					spam.setName(c.getName());
					spam.setNumber(c.getNumber());
					spam.setSpammer(true);
					Spam spam2= spamDao.save(spam);
					spams.add(spam2);
				}
				return "Marked as Spammer!";
			}
			
			
			
			
			spam.setName("Unknown");
			spam.setNumber(number);
			spam.setSpammer(true);
			Spam spam2= spamDao.save(spam);
			spams.add(spam2);
			return "Marked as Spammer!";
			
			
			
			
		}



		
		
		
}
