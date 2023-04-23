package com.gestione.dispositivi.aziendali.payload;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Registrazione_Dto {
	private String nome;
	private String username;
	private String email;
	private String password;
	private Set<String> ruoli;
}
