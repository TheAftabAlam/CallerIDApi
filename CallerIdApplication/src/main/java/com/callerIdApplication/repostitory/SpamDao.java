package com.callerIdApplication.repostitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.callerIdApplication.entity.Spam;
@Repository
public interface SpamDao extends JpaRepository<Spam, Integer>{
		public List<Spam> findBynumber(String number);
		public List<Spam> findByname(String name);
}
