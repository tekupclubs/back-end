package com.example.demo.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(exclude = {"image"})
@ToString(exclude = {"image"})
public class Evenement implements Serializable {
	private static final long serialVersionUID = -7446162716367847201L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id ;
	private String libelle;
	private Integer nbre_de_places;
	private String duree;
	private String localisation ;
	@Lob
	private byte[] evenementimage;
	@ManyToOne
	private Club club ;

	public Evenement(Integer id, String libelle, String duree, String localisation, Integer nbre_de_places,
					 byte[] evenementimage) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.localisation = localisation;
		this.duree = duree;
		this.nbre_de_places = nbre_de_places;
		this.evenementimage = evenementimage;

	}
	public Evenement() {super();}
}

