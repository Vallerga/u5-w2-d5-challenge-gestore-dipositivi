package com.gestione.dispositivi.aziendali.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gestione.dispositivi.aziendali.model.Dipendente;
import com.gestione.dispositivi.aziendali.model.Dispositivo;
import com.gestione.dispositivi.aziendali.repository.Dipendente_Dao;
import com.gestione.dispositivi.aziendali.repository.Dispositivo_Dao;

@Service
public class AssegnazioneService {
	@Autowired
	private Dipendente_Dao repoDipendente;
	@Autowired
	private Dispositivo_Dao repoDispositivo;

	@Autowired
	@Qualifier("FakeDipendente")
	private ObjectProvider<Dipendente> dipendenteProvider;
	@Autowired
	@Qualifier("FakeDispositivo")
	private ObjectProvider<Dispositivo> dispositivoProvider;

	public Dipendente insertDipendente(Dipendente d) {
		repoDipendente.save(d);
		return d;

	}

	public Dispositivo insertDispositivo(Dispositivo d) {
		repoDispositivo.save(d);
		return d;

	}

	public Dipendente creaDipendente() {
		return insertDipendente(dipendenteProvider.getObject());

	}

	public Dispositivo creaDispositivo() {
		return insertDispositivo(dispositivoProvider.getObject());
	}

	public List<Dispositivo> creaListaDispositivo(Dispositivo d) {
		List<Dispositivo> nuovaListaDispositivo = new ArrayList<Dispositivo>();
		d = repoDispositivo.save(dispositivoProvider.getObject());
		nuovaListaDispositivo.add(d);
		return nuovaListaDispositivo;
	}

	public List<Dipendente> creaListaDipendente(Dipendente d) {
		List<Dipendente> nuovaListaDipendente = new ArrayList<Dipendente>();
		d = repoDipendente.save(dipendenteProvider.getObject());
		nuovaListaDipendente.add(d);
		return nuovaListaDipendente;
	}

	public Dipendente getDipendente(Long id) {
		return repoDipendente.findById(id).get();
	}

	public Dispositivo getDispositivo(Long id) {
		return repoDispositivo.findById(id).get();
	}

	public List<Dipendente> getAllDipendente() {
		return (List<Dipendente>) repoDipendente.findAll();
	}

	public String delete(Long id) {
		repoDipendente.deleteById(id);
		repoDispositivo.deleteById(id);
		return "Dipendente Eliminato!!";
	}

	public Dipendente createDipendente(Dipendente dipendente) {
		repoDipendente.save(dipendente);
		return dipendente;
	}

	public Dipendente putDipendente(Dipendente dipendente) {
		repoDipendente.save(dipendente);
		return dipendente;
	}

}