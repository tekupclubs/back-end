package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.SponsorRepository;
import com.example.demo.models.Sponsor;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SponsorController {
	@Autowired
	private SponsorRepository sponsorrepo;

	@GetMapping("/sponsor")
	public List<Sponsor> getAllsponsor() {
		return sponsorrepo.findAll();
	}
	@GetMapping("/sponsor/{id}")
	public ResponseEntity<Sponsor> getSponsorById(@PathVariable(value = "id") Integer sponsorId)
			throws com.example.demo.exception.ResourceNotFoundException {
		Sponsor sponsor = sponsorrepo.findById(sponsorId)
				.orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Sponsor not found for this id :: " + sponsorId));
		return ResponseEntity.ok().body(sponsor);
	}

	@PostMapping("/sponsor")
	public Sponsor createSponsor(@Validated @RequestBody Sponsor sponsor) {
		return sponsorrepo.save(sponsor);
	}

	@PutMapping("/sponsor/{id}")
	public ResponseEntity<Sponsor> updateEmployee(@PathVariable(value = "id") Integer sponsorId,
			@Validated @RequestBody Sponsor sponsorDetails) throws com.example.demo.exception.ResourceNotFoundException {
		Sponsor sponsor = sponsorrepo.findById(sponsorId)
				.orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Sponsor not found for this id :: " + sponsorId));

		sponsor.setLibelle(sponsorDetails.getLibelle());
		sponsor.setBudget(sponsorDetails.getBudget());
		
		final Sponsor updatedsponsor = sponsorrepo.save(sponsor);
		return ResponseEntity.ok(updatedsponsor);
	}

	@DeleteMapping("/sponsor/{id}")
	public Map<String, Boolean> deleteSponsor(@PathVariable(value = "id")Integer sponsorId)
			throws com.example.demo.exception.ResourceNotFoundException {
		Sponsor sponsor = sponsorrepo.findById(sponsorId)
				.orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Sponsor not found for this id :: " + sponsorId));

		sponsorrepo.delete(sponsor);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
