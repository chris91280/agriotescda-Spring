package fr.greta91.cda.agriotes.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import fr.greta91.cda.agriotes.model.Evaluation;

public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {
	
	@Query("SELECT COUNT (id_evaluation) from Evaluation where intitule LIKE %?1%")
	int getEvaluationsCountByintitule(String searchWord);

	List<Evaluation> findAllByintituleContainingIgnoreCase(String searchWord, Pageable page);
	
	@Query("SELECT COUNT(id_evaluation) from Evaluation")
	int getEvaluationsCount();


	

}
