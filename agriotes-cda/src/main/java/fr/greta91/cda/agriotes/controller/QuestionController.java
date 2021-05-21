package fr.greta91.cda.agriotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.greta91.cda.agriotes.model.Question;
import fr.greta91.cda.agriotes.repo.QuestionRepository;
import fr.greta91.cda.agriotes.repo.QuestionRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@CrossOrigin(maxAge = 3600, origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class QuestionController {
	
	@Autowired
	QuestionRepository questionRepo;
	/*@GetMapping("/public/Questions")
	public List<Question> getQuestion(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber, 
											@RequestParam(value = "perPage", required = false, defaultValue = "10") int perPage, 
											@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord
											){
		Pageable page = PageRequest.of(pageNumber, perPage);
		List<Question> list = null;
		if (searchWord.length() > 0) {
			list = QuestionRepo.findAllByNomContainingIgnoreCase(searchWord, page);
		}
		else {
			Page<Question> pageQuestion = QuestionRepo.findAll(page);
			list = pageQuestion.getContent();
		}
//		List<String> list = new ArrayList<String>();
//		list.add("Question 1");
//		list.add("Question 2");
//		list.add("Question 3");
//		list.add("Question 4");
		return list;
	}
	
	@GetMapping("/public/count")
	public HashMap<String, Integer> getQuestionsCount(@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord
			) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (searchWord.length() > 0) {
			map.put("QuestionsCount", QuestionRepo.getQuestionsCountByNom(searchWord));
		} else {
			map.put("QuestionsCount", QuestionRepo.getQuestionsCount());
		}
		
		return map;
	}*/

	@GetMapping("/public/Questions/{id}")
	public ResponseEntity<Question> getQuestion(@PathVariable int id) {
		Optional<Question> optional = questionRepo.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/formateur/Questions/create")
	public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
		try {
			Question newQuestion = questionRepo.save(question);
			return ResponseEntity.ok(newQuestion);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("/formateur/Questions/edit")
	public ResponseEntity<Question> editQuestion(@RequestBody Question Question) {
		try {
			Question newQuestion = questionRepo.save(Question);
			return ResponseEntity.ok(newQuestion);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/formateur/Questions/delete/{id}")
	public ResponseEntity<String> DeleteQuestion(@PathVariable("id") int QuestionId) {
		try {
			questionRepo.deleteById(QuestionId);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
	
	


