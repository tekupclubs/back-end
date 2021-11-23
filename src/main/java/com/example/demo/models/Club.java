package com.example.demo.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	private String Libelle;
	private String Activite;
	private String email ;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Moderateur_id", nullable = false)
	private User Moderateur ;
	@OneToMany(mappedBy = "club" ,cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Evenement> evenements;
	
	@ManyToMany
	private List<Fournisseur> fournisseur;
	@ManyToMany
	private List<Sponsor> sponsors;
}
