package fr.greta91.cda.agriotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="groupe")
public class Groupe {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="id_groupe")
	private int idGroupe;
	@Column(name="nom_groupe")
	private String nomGroupe;
	
	
	public Groupe(int idGroupe, String nomGroupe) {
		super();
		this.idGroupe = idGroupe;
		this.nomGroupe = nomGroupe;
	}


	public Groupe() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getIdGroupe() {
		return idGroupe;
	}


	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}


	public String getNomGroupe() {
		return nomGroupe;
	}


	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}


	@Override
	public String toString() {
		return "Groupe [idGroupe=" + idGroupe + ", nomGroupe=" + nomGroupe + "]";
	}
	
	
	
	

}
