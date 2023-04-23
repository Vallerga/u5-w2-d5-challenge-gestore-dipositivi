package com.gestione.dispositivi.aziendali.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestione.dispositivi.aziendali.model.Dipendente;
import com.gestione.dispositivi.aziendali.model.Dispositivo;
import com.gestione.dispositivi.aziendali.service.AssegnazioneService;
@RestController
@RequestMapping("/assegnazione")
public class DipendeController {
	@Autowired
	AssegnazioneService service;

	 @GetMapping
	    public ResponseEntity<?> getAllDipendente() {
		return new ResponseEntity<List<Dipendente>>(service.getAllDipendente(), HttpStatus.OK);
	    }

	    @GetMapping("/dipendente/{id}")
	    public ResponseEntity<?> getDipendente(@PathVariable Long id) {
		return new ResponseEntity<Dipendente>(service.getDipendente(id), HttpStatus.OK);
	    }

	    @GetMapping("/dispositivo/{id}")
	    public ResponseEntity<?> getDispositivo(@PathVariable Long id) {
		return new ResponseEntity<Dispositivo>(service.getDispositivo(id), HttpStatus.OK);
	    }

	    @DeleteMapping("/dipendente/{id}")
	    public ResponseEntity<String> deleteDipendente(@PathVariable Long id) {
		return new ResponseEntity<String>(service.delete(id), HttpStatus.OK);
	    }

	    @PostMapping
	    public ResponseEntity<?> createDipendente(@RequestBody Dipendente dipendente) {
		return new ResponseEntity<Dipendente>(service.createDipendente(dipendente), HttpStatus.CREATED);

	    }

	    @PutMapping("/dipendente/{id}")
	    public ResponseEntity<?> putDipendente(@RequestBody Dipendente dipendente) {
		return new ResponseEntity<Dipendente>(service.putDipendente(dipendente), HttpStatus.CREATED);

	    }

}
