package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Sponsor;

public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {

}
