package com.gestione.dispositivi.aziendali.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ruoli")
public class Ruolo {
	private Long id;
	@Enumerated(EnumType.STRING)
	private Enum_Ruolo nome_ruolo;
}
