package model.beans;

import java.io.Serializable;

public class Ponuda implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3792725249348070651L;
	private int id;
	private Integer kompanijaId;
	private int cena;
	
	public Ponuda(){
		super();
	}
	
	public Ponuda(int id, Integer kompanijaId, int cena) {
		super();
		this.id = id;
		this.setKompanijaId(kompanijaId);
		this.cena = cena;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Integer getKompanijaId() {
		return kompanijaId;
	}

	public void setKompanijaId(Integer kompanijaId) {
		this.kompanijaId = kompanijaId;
	}

	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}