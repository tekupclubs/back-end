package com.example.demo.message;

import com.example.demo.models.Evenement;

import java.util.List;

import lombok.Data;
@Data
public class EvenementResponse {
    private String status;
    private String message;
    private String AUTH_TOKEN;
    private List<Evenement> oblist;

}
