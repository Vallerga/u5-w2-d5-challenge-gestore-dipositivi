package com.gestione.dispositivi.aziendali.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

public class Runnable implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {		
		System.out.println("Run...");

	}

}
