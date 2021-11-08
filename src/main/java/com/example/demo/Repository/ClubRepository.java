package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Club;

public interface ClubRepository extends JpaRepository<Club, Integer> {

}
