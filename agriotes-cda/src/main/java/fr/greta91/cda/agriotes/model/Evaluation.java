package fr.greta91.cda.agriotes.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "evaluation")
public class Evaluation implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_evaluation")
	private int id;
	@Column(name = "date_de_creation")
	public Date dateDeCreation;
	@Column(name = "intitule")
	private String intitule;
	@Column(name="description")
	private String description;
	@Column(name="durée")
	private int duree;
	@Column(name = "note")
	private int note = 0;
	@Column(name = "csv_file")
	private String csv_file;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Question> questions;

	public Evaluation() {
		super();
		questions = new ArrayList<Question>();
	}

	public int getId() {
		return id;
	}

	public void setId_evaluation(int id) {
		this.id = id;
	}

	public Date getDateDeCreation() {
		return dateDeCreation;
	}

	public void setDateDeCreation(Date dateDeCreation) {
		this.dateDeCreation = dateDeCreation;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCsv_file() {
		return csv_file;
	}

	public void setCsv_file(String csv_file) {
		this.csv_file = csv_file;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}

}
