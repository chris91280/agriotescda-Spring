package fr.greta91.cda.agriotes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="choix")
public class Choix {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_choix;
	private String choix;
	
	
	
	public Choix(int id_choix, String choix) {
		super();
		this.id_choix = id_choix;
		this.choix = choix;
	}



	public Choix() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId_choix() {
		return id_choix;
	}



	public void setId_choix(int id_choix) {
		this.id_choix = id_choix;
	}



	public String getChoix() {
		return choix;
	}



	public void setChoix(String choix) {
		this.choix = choix;
	}



	@Override
	public String toString() {
		return "Choix [id_choix=" + id_choix + ", choix=" + choix + "]";
	}
	
	
	
	

}
