package com.gestione.dispositivi.aziendali.service;

import com.gestione.dispositivi.aziendali.payload.Accesso_Dto;
import com.gestione.dispositivi.aziendali.payload.Registrazione_Dto;

public interface ServizioAutorizzazione {
	String accesso(Accesso_Dto accesso_Dto);
	String Registrazione(Registrazione_Dto Registrazione_Dto);
}
