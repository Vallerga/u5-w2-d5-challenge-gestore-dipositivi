package com.gestione.dispositivi.aziendali.configuration;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.gestione.dispositivi.aziendali.model.Dipendente;
import com.gestione.dispositivi.aziendali.model.Dispositivo;
import com.gestione.dispositivi.aziendali.model.StatoDispositivo;
import com.gestione.dispositivi.aziendali.model.TipoDispositivo;
import com.github.javafaker.Faker;

@Configuration
public class BeanConfiguration {
    @Bean("FakeDipendente")
    @Scope("prototype")
    public Dipendente fakeDipendente() {
    Faker fake = Faker.instance(new Locale("it-IT"));
    Dipendente d = new Dipendente();
    d.setNome(fake.name().firstName());
    d.setCognome(fake.name().lastName());
    d.setEmail(d.getNome() + "." + d.getCognome() + "@example.com");
    d.setUsername(fake.name().username());
    d.setPassword(fake.internet().password(6, 10, true, true));
    d.setLista_dispositivi(null);
    return d;
    }
    
    @Bean("FakeDispositivo")
	@Scope("prototype")
	public Dispositivo fakeDispositivo() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Dispositivo disp = new Dispositivo();
		int num = fake.number().numberBetween(1, 4);
		if (num == 1) {
			disp.setTipodisp(TipoDispositivo.LAPTOP);
		} else if (num == 2) {
			disp.setTipodisp(TipoDispositivo.SMARTOPHONE);
		} else if (num == 3) {
			disp.setTipodisp(TipoDispositivo.TABLET);
		} else {
			System.out.println("il palazzo va a fuoco scappate!!!");
		}
		num = fake.number().numberBetween(1, 5);
		if (num == 1) {
			disp.setStatodisp(StatoDispositivo.ASSEGNATO);
		} else if (num == 2) {
			disp.setStatodisp(StatoDispositivo.DISPONIBILE);
		} else if (num == 3) {
			disp.setStatodisp(StatoDispositivo.IN_MANUTENZIONE);
		}else if (num == 4) {
			disp.setStatodisp(StatoDispositivo.DISMESSO);
		} else {
			System.out.println("il palazzo va a fuoco scappate!!!");
		}
		disp.setLista_dipendenti(null);
		

		return disp;
	}

}
