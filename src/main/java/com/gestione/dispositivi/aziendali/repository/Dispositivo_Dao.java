package com.gestione.dispositivi.aziendali.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestione.dispositivi.aziendali.model.Dispositivo;

public interface Dispositivo_Dao extends JpaRepository<Dispositivo, Long> {

}
