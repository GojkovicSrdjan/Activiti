package model.beans;

import java.io.Serializable;

public class AgentKompanije implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3792725249348070651L;
	private String id;
	private String ime;
	private String prezime;
	private String password;
	private String email;
	private Integer idKomanije;
	
	public AgentKompanije(String id, String ime, String prezime, String password, String email) {
		super();
		this.id=id;
		this.ime = ime;
		this.prezime = prezime;
		this.password = password;
		this.email = email;
	}

	public AgentKompanije(){
		super();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdKomanije() {
		return idKomanije;
	}

	public void setIdKomanije(Integer idKomanije) {
		this.idKomanije = idKomanije;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
