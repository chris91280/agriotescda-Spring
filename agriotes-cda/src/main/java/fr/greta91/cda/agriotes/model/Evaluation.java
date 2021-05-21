package fr.greta91.cda.agriotes.model;

import java.io.File;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "evaluation")
public class Evaluation {
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name="id_evaluation")
	private int id_evaluation;
	@Column(name="date_de_creation")
	public Date dateDeCreation ;
	@Column(name="intitule")
	private String intitule;
	@Column(name="note")
	private int note;
	@Column(name="csv_file")
	private String csv_file;
	
	public Evaluation(int id_evaluation, Date dateDeCreation, String intitule, int note, String csv_file) {
		super();
		this.id_evaluation = id_evaluation;
		this.dateDeCreation = dateDeCreation;
		this.intitule = intitule;
		this.note = note;
		this.csv_file = csv_file;
	}


	public Evaluation() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId_evaluation() {
		return id_evaluation;
	}


	public void setId_evaluation(int id_evaluation) {
		this.id_evaluation = id_evaluation;
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


	@Override
	public String toString() {
		return "Evaluation [id_evaluation=" + id_evaluation + ", dateDeCreation=" + dateDeCreation + ", intitule="
				+ intitule + ", note=" + note + ", csv_file=" + csv_file + "]";
	}
	
	
	
	
	

	
	
	
	
	

	
	
	
	
	
	
	


	
	}
	
	
	
	
	
	
	
	
	


