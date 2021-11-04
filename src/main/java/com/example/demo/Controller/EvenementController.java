package com.example.demo.Controller;


import com.example.demo.Repository.EvenementRepository;
import com.example.demo.models.Evenement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class EvenementController {
    @Autowired
    private EvenementRepository evenementrepo;
    @GetMapping("/evenement")
    public List<Evenement> getAllevenement() {
        return evenementrepo.findAll();
    }
    @GetMapping("/evenement/{id}")
    public ResponseEntity<Evenement> getevenementById(@PathVariable(value = "id") Integer evenementId)
            throws com.example.demo.exception.ResourceNotFoundException {
        Evenement evenement = evenementrepo.findById(evenementId)
                .orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Event not found for this id :: " + evenementId));
        return ResponseEntity.ok().body(evenement);
    }
    @PostMapping("/evenement")
    public Evenement createEvenement(@Validated @RequestBody Evenement evenement) {
        return evenementrepo.save(evenement);
    }

    @PutMapping("/evenement/{id}")
    public ResponseEntity<Evenement> updateEmployee(@PathVariable(value = "id") Integer evenementId,
                                                  @Validated @RequestBody Evenement evenementDetails) throws com.example.demo.exception.ResourceNotFoundException {
        Evenement evenement = evenementrepo.findById(evenementId)
                .orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("event not found for this id :: " + evenementId));

        evenement.setLibelle(evenementDetails.getLibelle());
        evenement.setNbre_de_places(evenementDetails.getNbre_de_places());

        final Evenement updatedevenement = evenementrepo.save(evenement);
        return ResponseEntity.ok(updatedevenement);
    }

    @DeleteMapping("/evenement/{id}")
    public Map<String, Boolean> deleteEvenement(@PathVariable(value = "id")Integer evenementId)
            throws com.example.demo.exception.ResourceNotFoundException {
        Evenement evenement = evenementrepo.findById(evenementId)
                .orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("Event not found for this id :: " + evenementId));

        evenementrepo.delete(evenement);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
