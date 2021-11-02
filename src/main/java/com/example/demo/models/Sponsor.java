package com.example.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Sponsor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	
	private String Libelle ;
	private Float budget;
	@ManyToMany(mappedBy = "sponsors")
	private List<Club> clubs;
	@ManyToOne
	private User Moderateur;
	
	
	
	

}
