package com.gestione.dispositivi.aziendali.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestione.dispositivi.aziendali.entity.Enum_Ruolo;
import com.gestione.dispositivi.aziendali.entity.Ruolo;
import com.gestione.dispositivi.aziendali.entity.Utente;
import com.gestione.dispositivi.aziendali.exception.MyAPIException;
import com.gestione.dispositivi.aziendali.payload.Accesso_Dto;
import com.gestione.dispositivi.aziendali.payload.Registrazione_Dto;
import com.gestione.dispositivi.aziendali.repository.Ruolo_Dao;
import com.gestione.dispositivi.aziendali.repository.Utente_Dao;
import com.gestione.dispositivi.aziendali.security.JwtTokenProvider;

@Service
public class ImplementazioneServizioAutorizzazione implements ServizioAutorizzazione {

	private AuthenticationManager authenticationManager;
	private Utente_Dao utente_Dao;
	private Ruolo_Dao ruolo_Dao;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;

	public ImplementazioneServizioAutorizzazione(AuthenticationManager authenticationManager,
							Utente_Dao utente_Dao,
							Ruolo_Dao ruolo_Dao, 
							PasswordEncoder passwordEncoder, 
							JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.ruolo_Dao = ruolo_Dao;
		this.utente_Dao = utente_Dao;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
    public String accesso(Accesso_Dto Accesso_Dto) {
        
    	Authentication authentication = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(
        				Accesso_Dto.getUsername(), Accesso_Dto.getPassword()
        		)
        ); 
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

	@Override
	public String Registrazione(Registrazione_Dto Registrazione_Dto) {
		
        if(utente_Dao.existsByUsername(Registrazione_Dto.getUsername())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "L'Username esiste già!.");
        }
        
        if(utente_Dao.existsByEmail(Registrazione_Dto.getEmail())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "L'Email esiste già!.");
        }
        Utente Utente = new Utente();
        Utente.setName(Registrazione_Dto.getNome());
        Utente.setUsername(Registrazione_Dto.getUsername());
        Utente.setEmail(Registrazione_Dto.getEmail());
        Utente.setPassword(passwordEncoder.encode(Registrazione_Dto.getPassword()));
        
        Set<Ruolo> ruoli = new HashSet<>();
        
        if(Registrazione_Dto.getRuoli() != null) {
        	Registrazione_Dto.getRuoli().forEach(ruolo -> {
        		Ruolo RuoloUtente = ruolo_Dao.findByNomeRuolo(getRuolo(ruolo)).get();
	        	ruoli.add(RuoloUtente);
	        });
        } else {
        	Ruolo RuoloUtente = ruolo_Dao.findByNomeRuolo(Enum_Ruolo.RUOLO_UTENTE).get();
        	ruoli.add(RuoloUtente);
        }
        
        Utente.setRuoli(ruoli);
        System.out.println(Utente);
        utente_Dao.save(Utente);
        
        return "Registrazione Utente Avvenuta!.";
	}
	
	public Enum_Ruolo getRuolo(String ruolo) {
		if(ruolo.equals("RUOLO_AMMINISTRATORE")) return Enum_Ruolo.RUOLO_AMMINISTRATORE;
		else if(ruolo.equals("RUOLO_MODERATORE")) return Enum_Ruolo.RUOLO_MODERATORE;
		else return Enum_Ruolo.RUOLO_UTENTE;
	}
}
