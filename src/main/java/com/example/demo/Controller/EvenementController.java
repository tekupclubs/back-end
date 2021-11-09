package com.example.demo.Controller;

import com.example.demo.exception.EvenementCustomException;
import com.example.demo.message.EvenementResponse;
import com.example.demo.Repository.EvenementRepository;
import com.example.demo.message.ResponseCode;
import com.example.demo.message.ServerResponse;
import com.example.demo.models.Evenement;
import com.example.demo.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import java.io.IOException;

@CrossOrigin()
@RestController
@RequestMapping("/api")

public class EvenementController {


    @Autowired
    private EvenementRepository evenementRepository;
    @Autowired
    private JmsTemplate jmsTemplate;


    public EvenementController() {
        super();
    }


    @PostMapping("/addevenement")
    public ResponseEntity<EvenementResponse> addevenement(
            @RequestParam(name ="Libelle") String Libelle,
            @RequestParam(name ="Nbre_de_places") String Nbre_de_places,
            @RequestParam(name ="Localisation") String Localisation,
            @RequestParam(name ="Duree" ) String Duree,
            @RequestParam(name="file") MultipartFile evenementimage
            )


             throws IOException {
        EvenementResponse evres = new EvenementResponse();
        if (Validator.isStringEmpty(Libelle) || Validator.isStringEmpty(Localisation)
                || Validator.isStringEmpty(Duree) ||  Validator.isStringEmpty(Nbre_de_places)) {
            evres.setStatus("400");
            evres.setMessage("BAD_REQUEST");
            return new ResponseEntity<EvenementResponse>(evres, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                Evenement eve = new Evenement();
                eve.setLibelle(Libelle);
                eve.setLocalisation(Localisation);
                eve.setDuree(Duree);
                eve.setNbre_de_places(Integer.parseInt(Nbre_de_places));
                if (evenementimage != null) {
                    eve.setEvenementimage(evenementimage.getBytes());
                }
                evenementRepository.save(eve);
                jmsTemplate.convertAndSend("evenmentque", eve);
                evres.setStatus("200");
                evres.setMessage("SUCCESS");
                evres.setOblist(evenementRepository.findAll());
            } catch (Exception e) {
                throw new EvenementCustomException(e.getMessage());
            }
        }
        return new ResponseEntity<EvenementResponse>(evres, HttpStatus.OK);
    }


    @PutMapping("/updateevenement")
    public ResponseEntity<ServerResponse> updateEvenement(

            @RequestParam(name ="Libelle") String Libelle,
            @RequestParam(name ="Nbre_de_places") String Nbre_de_places,
            @RequestParam(name ="Localisation") String Localisation,

            @RequestParam(name ="Duree" ) String Duree,
            @RequestParam(name="file") MultipartFile evenementimage,
            @RequestParam(name ="id") String evenementid) throws IOException {
        ServerResponse evres = new ServerResponse();
        if (Validator.isStringEmpty(Libelle) || Validator.isStringEmpty(Duree)
                || Validator.isStringEmpty(Nbre_de_places) || Validator.isStringEmpty(Localisation))
        {	evres.setStatus("400");
            evres.setMessage("BAD_REQUEST");
            return new ResponseEntity<ServerResponse>(evres, HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            try {
                if ( evenementimage != null) {


                    Evenement evenement =  new Evenement(Integer.parseInt(evenementid), Libelle,Duree,Localisation,
                             Integer.parseInt(Nbre_de_places), evenementimage.getBytes());
                    evenementRepository.save(evenement);
                } else {
                    Evenement evenementOrg = evenementRepository.findByid(Integer.parseInt(evenementid));
                    Evenement evenement1 =   new Evenement(Integer.parseInt(evenementid), Libelle,Localisation,Duree,
                            Integer.parseInt(Nbre_de_places),evenementimage.getBytes()) ;
                    evenementRepository.save(evenement1);
                    evres.setStatus("200");
                    evres.setMessage("SUCCESS");
                }

            } catch (Exception e) {
                throw new EvenementCustomException("Unable to update Evenement details, please try again");
            }
        }
        return new ResponseEntity<ServerResponse>(evres, HttpStatus.OK);
    }



    @DeleteMapping("/deletevenement")
    public ResponseEntity<EvenementResponse> delProduct(@RequestParam(name = "id") String evenemntid)
            throws IOException {
        EvenementResponse evres = new EvenementResponse();
        if (Validator.isStringEmpty(evenemntid)) {
            evres.setStatus("400");
            evres.setMessage("BAD_REQUEST");
            return new ResponseEntity<EvenementResponse>(evres, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                evenementRepository.deleteById(Integer.parseInt(evenemntid));
                evres.setStatus("200");
                evres.setMessage("SUCCESS");
            } catch (Exception e) {
                throw new EvenementCustomException(e.getMessage());

            }
            return new ResponseEntity<EvenementResponse>(evres, HttpStatus.OK);}
    }

    @GetMapping("/getevenements")
    public ResponseEntity<EvenementResponse> getEvenement() throws IOException {
        EvenementResponse eve = new EvenementResponse();
        try {
            eve.setStatus(ResponseCode.SUCCESS_CODE);
            eve.setMessage(ResponseCode.LIST_SUCCESS_MESSAGE);
            eve.setOblist(evenementRepository.findAll());
        } catch (Exception e) {
            throw new EvenementCustomException("Unable to retrieve evenement, please try again");
        }
        return new ResponseEntity<EvenementResponse>(eve, HttpStatus.OK);
    }


}
