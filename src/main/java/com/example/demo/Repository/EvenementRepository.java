package com.example.demo.Repository;


import com.example.demo.models.Evenement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvenementRepository extends JpaRepository<Evenement, Integer> {

    Evenement findByid(int evenementid);

    void deleteByid(int evenementid);

    List<Evenement> findAll();
}
