package com.example.demo.models;

import lombok.Data;

@Data
public class SignupRequest {
	private String userName; 
	private String email;
	private int telephone;
	private String password;
	private String[] roles;

}
