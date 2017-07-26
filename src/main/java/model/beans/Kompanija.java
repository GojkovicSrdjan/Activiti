package model.beans;

import java.io.Serializable;

public class Kompanija implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8727482613761473929L;
	private Integer id;
	private String nazivKompanije;
	private String email;
	private boolean prihvacenPosao;
	private Integer kategorijaId;
	private String agentId;
	private long ponuda;
	
	public Kompanija(){
		super();
	}
	
	public Kompanija(Integer id, String nazivKompanije, String email) {
		super();
		this.id = id;
		this.nazivKompanije = nazivKompanije;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNazivKompanije() {
		return nazivKompanije;
	}
	public void setNazivKompanije(String nazivKompanije) {
		this.nazivKompanije = nazivKompanije;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isPrihvacenPosao() {
		return prihvacenPosao;
	}
	public void setPrihvacenPosao(boolean prihvacenPosao) {
		this.prihvacenPosao = prihvacenPosao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getKategorijaId() {
		return kategorijaId;
	}

	public void setKategorijaId(Integer kategorijaId) {
		this.kategorijaId = kategorijaId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public long getPonuda() {
		return ponuda;
	}

	public void setPonuda(long ponuda) {
		this.ponuda = ponuda;
	}

}
