package fr.greta91.cda.agriotes.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.greta91.cda.agriotes.model.Canal;


public interface CanalRepository extends JpaRepository<Canal,Integer> {

	List<Canal> findAllBynomCanalContainingIgnoreCase(String searchWord, Pageable page);

	@Query("SELECT COUNT (id_canal) from Canal where intitule LIKE %?1%")
	Integer getCanauxCountByNomCanal(String searchWord);
	
	
	@Query("SELECT COUNT(id_canal) from Canal")
	Integer getCanauxCount();

}
