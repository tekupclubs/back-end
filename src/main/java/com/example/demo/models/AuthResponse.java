package com.example.demo.models;

import java.util.List;

import lombok.Data;

@Data
public class AuthResponse {

	private String token ;
	private List<String> roles ;
	
	
}
