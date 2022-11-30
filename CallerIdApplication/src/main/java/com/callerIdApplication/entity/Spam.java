package com.callerIdApplication.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Spam {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Integer spamID;
	private String name;
	private String number;
	private Boolean spammer;
	//List<Spam> spammerList=new ArrayList<>();
	

}
