package com.gestione.dispositivi.aziendali.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dipendenti")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dispositivo {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false, unique = true)
	@Enumerated(EnumType.STRING)
	private StatoDispositivo statodisp;
	@Enumerated(EnumType.STRING)
	private TipoDispositivo tipodisp;
	@ManyToMany
	private List<Dipendente> lista_dipendenti;
	

}
