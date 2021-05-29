package fr.greta91.cda.agriotes.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.greta91.cda.agriotes.model.Groupe;

public interface GroupeRepository extends JpaRepository<Groupe,Integer> {

	List<Groupe> findAllByNomGroupeContainingIgnoreCase(String searchWord, Pageable page);
	
	
	  @Query("SELECT COUNT(id_groupe) from Groupe")
	  Integer getGroupesCount();
	  
	  @Query("SELECT COUNT (id_groupe) from Groupe where nom_groupe LIKE %?1%")
	  Integer getGroupesCountByNom(String searchWord);
	 



}
