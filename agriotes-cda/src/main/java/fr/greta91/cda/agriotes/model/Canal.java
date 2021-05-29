package fr.greta91.cda.agriotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="canal")
public class Canal {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="id_canal")
	private int id;
	@Column(name="nom_canal")
	private String nomCanal;
	
	
	public Canal(int id, String nomCanal) {
		super();
		this.id = id;
		this.nomCanal = nomCanal;
	}


	public Canal() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomCanal() {
		return nomCanal;
	}


	public void setNomCanal(String nomCanal) {
		this.nomCanal = nomCanal;
	}


	@Override
	public String toString() {
		return "Canal [id=" + id + ", nomCanal=" + nomCanal + "]";
	}
	
	
	
	

}

