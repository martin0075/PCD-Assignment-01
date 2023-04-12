package com.swengfinal.project.shared;

import java.io.Serializable;

public class IscrizioneEsame implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public IscrizioneEsame() {}
	
	private int idEsame;
	private String mailStudente;
	
	public IscrizioneEsame(int idEsame, String mailStudente) {
		this.idEsame=idEsame;
		this.mailStudente=mailStudente;
	}
	
	public int getIdEsame() {
		return idEsame;
	}
	
	public void setIdCorso(int idEsame) {
		this.idEsame = idEsame;
	}
	
	public String getMailStudente() {
		return mailStudente;
	}
	
	public void setMailStudente(String mailStudente) {
		this.mailStudente = mailStudente;
	}

}
