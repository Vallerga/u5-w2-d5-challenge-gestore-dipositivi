package com.gestione.dispositivi.aziendali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestione.dispositivi.aziendali.model.Dipendente;

public interface Dipendente_Dao extends JpaRepository<Dipendente, Long> {

}
