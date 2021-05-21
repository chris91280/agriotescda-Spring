package fr.greta91.cda.agriotes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="question")
public class Question {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id_question;
	private String ennonce;
	private String choix_reponse;
	
	
	
	public Question(int id_question, String ennonce, String choix_reponse) {
		super();
		this.id_question = id_question;
		this.ennonce = ennonce;
		this.choix_reponse = choix_reponse;
	}



	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}



	public long getId_question() {
		return id_question;
	}



	public void setId_question(int id_question) {
		this.id_question = id_question;
	}



	public String getEnnonce() {
		return ennonce;
	}



	public void setEnnonce(String ennonce) {
		this.ennonce = ennonce;
	}


	public String getChoix_reponse() {
		return choix_reponse;
	}



	public void setChoix_reponse(String choix_reponse) {
		this.choix_reponse = choix_reponse;
	}



	@Override
	public String toString() {
		return "Question [id_question=" + id_question + ", ennonce=" + ennonce + ", choix_reponse="
				+ choix_reponse + "]";
	}
	
	
	

	
	

}
