package com.callerIdApplication.repostitory;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.callerIdApplication.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer>{

	public User findByphoneNumber(String mobileNo);
	
	public User findByuserName(String userName);
	
	

}
