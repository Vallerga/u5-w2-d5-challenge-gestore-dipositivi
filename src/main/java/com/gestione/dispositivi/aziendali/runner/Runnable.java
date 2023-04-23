package com.gestione.dispositivi.aziendali.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.gestione.dispositivi.aziendali.service.AssegnazioneService;

@Component
public class Runnable implements ApplicationRunner {
    @Autowired
    AssegnazioneService service;

    @Override
    public void run(ApplicationArguments args) throws Exception {
	System.out.println("Run...");

	service.creaDispositivo();
	System.out.println("Dispositivo Creato!!");
	service.creaDipendente();
	System.out.println("Dipendente Creato!!");
    }

}