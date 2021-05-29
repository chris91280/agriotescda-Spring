package fr.greta91.cda.agriotes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class Question implements Serializable {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String ennonce;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Choix> choix;
	@OneToOne(cascade = CascadeType.ALL)
	private Choix choixCorrect;

	public Question() {
		super();
		choix = new ArrayList<Choix>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnnonce() {
		return ennonce;
	}
	public void setEnnonce(String ennonce) {
		this.ennonce = ennonce;
	}

	public List<Choix> getChoix() {
		return choix;
	}

	public void setChoix(List<Choix> choix) {
		this.choix = choix;
	}
	
	public void addChoix(Choix choix) {
		this.choix.add(choix);
	}

	public Choix getChoixCorrect() {
		return choixCorrect;
	}
	
	public void setChoixCorrect(Choix choixCorrect) {
		this.choixCorrect = choixCorrect;
	}

}
