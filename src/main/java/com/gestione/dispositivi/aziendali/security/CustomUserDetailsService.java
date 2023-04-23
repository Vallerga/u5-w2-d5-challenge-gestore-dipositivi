package com.gestione.dispositivi.aziendali.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gestione.dispositivi.aziendali.entity.Utente;
import com.gestione.dispositivi.aziendali.repository.Utente_Dao;

public class CustomUserDetailsService implements UserDetailsService {
	
	private Utente_Dao utente_Dao;
	
	public CustomUserDetailsService(Utente_Dao utente_Dao) {
		this.utente_Dao = utente_Dao;
	}
	
	 @Override
	    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		 Utente utente = utente_Dao.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				 .orElseThrow(() ->
				 new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));
		 
		 Set<GrantedAuthority> authorities = utente
				 .getRuoli()
				 .stream()
				 .map((ruolo) -> new SimpleGrantedAuthority(ruolo.getNome_ruolo().toString())).collect(Collectors.toSet());
		 
		 return new org.springframework.security.core.userdetails.User(utente.getEmail(),
				 utente.getPassword(),
				 authorities);
	 }
}
