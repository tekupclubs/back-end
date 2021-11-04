package com.example.demo.Repository;


import com.example.demo.models.Evenement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Integer> {

}
