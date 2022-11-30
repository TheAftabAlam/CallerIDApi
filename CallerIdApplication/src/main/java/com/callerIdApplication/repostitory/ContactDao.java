package com.callerIdApplication.repostitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.callerIdApplication.entity.Contact;
@Repository
public interface ContactDao extends JpaRepository<Contact, Integer>{
	
	@Query(value="select * from Contact as c where c.name like %:name% ", nativeQuery = true)
	public List<Contact> getContactbykeywords(@Param("name") String name);

	@Query("from Contact as c where c.number=:num")
	public List<Contact> getAllContactByphoneNumber(@Param("num") String phoneNumber);
}
