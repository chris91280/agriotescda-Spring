package fr.greta91.cda.agriotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "efg")
public class Efg {
	@Id
	@Column(name="id_efg")
	private int idEfg;
	@Column(name="intitulé_efg")
	private String intituléEfg;
	
	
	
	public Efg(int idEfg, String intituléEfg) {
		super();
		this.idEfg = idEfg;
		this.intituléEfg = intituléEfg;
	}



	public Efg() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getIdEfg() {
		return idEfg;
	}



	public void setIdEfg(int idEfg) {
		this.idEfg = idEfg;
	}



	public String getIntituléEfg() {
		return intituléEfg;
	}



	public void setIntituléEfg(String intituléEfg) {
		this.intituléEfg = intituléEfg;
	}



	@Override
	public String toString() {
		return "Efg [idEfg=" + idEfg + ", intituléEfg=" + intituléEfg + "]";
	}
	
	
	
	
	
	
	


}
