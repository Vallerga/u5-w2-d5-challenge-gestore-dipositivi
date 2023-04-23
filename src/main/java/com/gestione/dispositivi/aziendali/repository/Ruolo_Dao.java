package com.gestione.dispositivi.aziendali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestione.dispositivi.aziendali.entity.Enum_Ruolo;
import com.gestione.dispositivi.aziendali.entity.Ruolo;

import java.util.Optional;

public interface Ruolo_Dao extends JpaRepository<Ruolo, Long> {
    
	Optional<Ruolo> findByNomeRuolo(Enum_Ruolo nome_Ruolo);

}
