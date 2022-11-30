package com.callerIdApplication.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callerIdApplication.entity.CurrentUserSession;



public interface SessionDao extends JpaRepository<CurrentUserSession, Integer> {

	
	public  CurrentUserSession  findByUuid(String uuid);
}
