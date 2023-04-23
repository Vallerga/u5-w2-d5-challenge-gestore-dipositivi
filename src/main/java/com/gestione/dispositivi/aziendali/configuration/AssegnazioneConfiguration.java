package com.gestione.dispositivi.aziendali.configuration;

import java.util.List;
import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.gestione.dispositivi.aziendali.model.Dipendente;
import com.gestione.dispositivi.aziendali.model.Dispositivo;
import com.gestione.dispositivi.aziendali.model.StatoDispositivo;
import com.gestione.dispositivi.aziendali.model.TipoDispositivo;
import com.gestione.dispositivi.aziendali.repository.Dipendente_Dao;
import com.gestione.dispositivi.aziendali.repository.Dispositivo_Dao;
import com.github.javafaker.Faker;
import com.gestione.dispositivi.aziendali.security.JwtAuthenticationEntryPoint;
import com.gestione.dispositivi.aziendali.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class AssegnazioneConfiguration {
	
	private UserDetailsService userDetailsService;
	
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;
	
    public AssegnazioneConfiguration(UserDetailsService userDetailsService,
            JwtAuthenticationEntryPoint authenticationEntryPoint,
            JwtAuthenticationFilter authenticationFilter){
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }
    
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	http.cors().and().csrf().disable()
        .authorizeHttpRequests((authorize) -> authorize
        		.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated())
        .exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint)
        ).sessionManagement( session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

    	http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    	return http.build();
    }
    
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