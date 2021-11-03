package com.example.demo.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	@Column(name="name")
	private String userName ;
	@Column(name="email")
	private String email ;
	@Column(name="telephone")
	private int telephone;
	@Column(name="password")
	private String password ;
	@ManyToMany(fetch = FetchType.LAZY)
	  @JoinTable(name = "user_role", 
	      joinColumns = @JoinColumn(name="USER_ID", referencedColumnName="ID"),
	      inverseJoinColumns = @JoinColumn(name="ROLE_ID", referencedColumnName="ID"))
	private Set<Role> roles = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,mappedBy = "Moderateur")
	@JsonIgnore
	private Club club; 
	
	@OneToMany(mappedBy = "Moderateur")
	@JsonIgnore
	private List<Fournisseur> fournisseur; 
	
	@OneToMany(mappedBy = "Moderateur")
	@JsonIgnore
	private List<Sponsor> sponsors; 
	
	

}
