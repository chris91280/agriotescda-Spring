package fr.greta91.cda.agriotes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.greta91.cda.agriotes.model.Question;

public interface QuestionRepository extends JpaRepository<Question,Integer> {

}
