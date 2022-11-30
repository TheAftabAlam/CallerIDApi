package com.callerIdApplication.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class User {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	
		private Integer userId;
		@NotNull
		private String userName;
		@NotNull
		private String phoneNumber;
		
		private String email;
		
		@NotNull
		private String password;
		
		@OneToMany(cascade = CascadeType.ALL)
		@JsonIgnore
		private List<Contact> contacts=new ArrayList<>();
		
}
