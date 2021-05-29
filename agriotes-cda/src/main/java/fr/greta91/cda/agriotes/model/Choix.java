package fr.greta91.cda.agriotes.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="choix")
public class Choix implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String choix;
		public Choix() {
		super();
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	@Override
	public String toString() {
		return "Choix [id=" + id + ", choix=" + choix + "]";
	}
}
