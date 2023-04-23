package com.gestione.dispositivi.aziendali.configuration;

import java.util.List;
import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gestione.dispositivi.aziendali.model.Dipendente;
import com.gestione.dispositivi.aziendali.model.Dispositivo;
import com.gestione.dispositivi.aziendali.model.StatoDispositivo;
import com.gestione.dispositivi.aziendali.model.TipoDispositivo;
import com.gestione.dispositivi.aziendali.repository.Dipendente_Dao;
import com.gestione.dispositivi.aziendali.repository.Dispositivo_Dao;
import com.github.javafaker.Faker;

@Configuration
public class AssegnazioneConfiguration {

    @Autowired
    Dispositivo_Dao dispositivoDao;
    @Autowired
    Dipendente_Dao dipendenteDao;

    @Bean("FakeDipendente")
    @Scope("prototype")

    Dipendente fakeDipendente() {
	Faker fake = Faker.instance(new Locale("it-IT"));
	Dipendente d = new Dipendente();
	d.setNome(fake.name().firstName());
	d.setCognome(fake.name().lastName());
	d.setEmail(d.getNome() + "." + d.getCognome() + "@example.com");
	d.setUsername(fake.name().username());
	d.setPassword(fake.internet().password(6, 10, true, true));
	d.setLista_dispositivi((List<Dispositivo>) dispositivoDao.findAll());
	return d;
    }

    @Bean("FakeDispositivo")
    @Scope("prototype")
    Dispositivo fakeDispositivo() {
	Faker fake = Faker.instance(new Locale("it-IT"));
	Dispositivo disp = new Dispositivo();
	int num = fake.number().numberBetween(1, 4);
	if (num == 1) {
	    disp.setTipoDisp(TipoDispositivo.LAPTOP);
	} else if (num == 2) {
	    disp.setTipoDisp(TipoDispositivo.SMARTPHONE);
	} else if (num == 3) {
	    disp.setTipoDisp(TipoDispositivo.TABLET);
	} else {
	    System.out.println("il palazzo va a fuoco scappate!!!");
	}
	num = fake.number().numberBetween(1, 5);
	if (num == 1) {
	    disp.setStatoDisp(StatoDispositivo.ASSEGNATO);
	} else if (num == 2) {
	    disp.setStatoDisp(StatoDispositivo.DISPONIBILE);
	} else if (num == 3) {
	    disp.setStatoDisp(StatoDispositivo.IN_MANUTENZIONE);
	} else if (num == 4) {
	    disp.setStatoDisp(StatoDispositivo.DISMESSO);
	} else {
	    System.out.println("il palazzo va a fuoco scappate!!!");
	}
	disp.setLista_dipendenti((List<Dipendente>) dipendenteDao.findAll());
	return disp;
    }
}