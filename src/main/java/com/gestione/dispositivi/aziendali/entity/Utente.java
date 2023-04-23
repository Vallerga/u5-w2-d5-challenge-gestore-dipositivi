package com.gestione.dispositivi.aziendali.entity;

import java.util.HashSet;
import java.util.Set;

import com.gestione.dispositivi.aziendali.model.Dipendente;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Utenti", uniqueConstraints = { @UniqueConstraint(columnNames = "username") })
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false, unique = true)
	private String email;
	private String password;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "utente")
    private Dipendente dipendente;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinTable(name = "utenti_ruoli",
			joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName ="id")
	)
	private Set<Ruolo> ruoli = new HashSet<>();

}
