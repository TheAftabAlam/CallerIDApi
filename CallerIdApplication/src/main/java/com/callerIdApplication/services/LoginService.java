package com.callerIdApplication.services;

import com.callerIdApplication.entity.LoginDTO;
import com.callerIdApplication.exceptions.LoginException;

public interface LoginService {
	
	public String logIntoAccount(LoginDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
