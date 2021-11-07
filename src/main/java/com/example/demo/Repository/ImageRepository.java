package com.example.demo.Repository;

import com.example.demo.models.ImageDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageDb, String> {

    }


