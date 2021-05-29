package fr.greta91.cda.agriotes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
import org.springframework.web.multipart.MultipartFile;

import fr.greta91.cda.agriotes.helper.CSVHelper;
import fr.greta91.cda.agriotes.message.ResponseMessage;
import fr.greta91.cda.agriotes.model.Evaluation;
import fr.greta91.cda.agriotes.model.Question;
import fr.greta91.cda.agriotes.repo.EvaluationRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@CrossOrigin(maxAge = 3600, origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EvaluationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EvaluationController.class);
	@Autowired
	EvaluationRepository evaluationRepo;
	@GetMapping("/public/evaluations")
	public List<Evaluation> getEvaluation(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber, 
											@RequestParam(value = "perPage", required = false, defaultValue = "10") int perPage, 
											@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord
											){
		Pageable page = PageRequest.of(pageNumber, perPage);
		List<Evaluation> list = null;
		if (searchWord.length() > 0) {
			list = evaluationRepo.findAllByintituleContainingIgnoreCase(searchWord, page);
		}
		else {
			list = evaluationRepo.findAll();
			//Page<Evaluation> pageEvaluation = evaluationRepo.findAll(page);
			//list = pageEvaluation.getContent();
		}
//		List<String> list = new ArrayList<String>();
//		list.add("Evaluation 1");
//		list.add("Evaluation 2");
//		list.add("Evaluation 3");
//		list.add("Evaluation 4");
		return list;
	}
	
	@GetMapping("/public/evaluations/count")
	public HashMap<String, Integer> getEvaluationsCount(@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord
			) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (searchWord.length() > 0) {
			map.put("EvaluationsCount", evaluationRepo.getEvaluationsCountByintitule(searchWord));
		} else {
			map.put("EvaluationsCount", evaluationRepo.getEvaluationsCount());
		}
		
		return map;
	}

	@GetMapping("/public/evaluations/{id}")
	public ResponseEntity<Evaluation> getEvaluation(@PathVariable int id) {
		Optional<Evaluation> optional = evaluationRepo.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
//	@PostMapping("/formateur/evaluations/create")
//	public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation Evaluation ) {
//		
//		try {
//			Evaluation newEvaluation = evaluationRepo.save(Evaluation);
//			return ResponseEntity.ok(newEvaluation);
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().build();
//		}
//			
//	}
	@Transactional
	@PostMapping("/formateur/evaluations/create")
	public ResponseEntity<ResponseMessage> createEvaluation(@RequestParam("file") MultipartFile file, 
																@RequestParam("intitule") String intitule,
																@RequestParam("duree") int duree,
																@RequestParam("description") String description
																) {
		LOGGER.error("dans createEvaluation");
		LOGGER.error("intitule " + intitule);
		try {
//			if(!CSVHelper.hasCSVFormat(file)) throw new Exception();
			
			List<Question> questions = CSVHelper.csvToQuestions(file.getInputStream());
			
			Evaluation eval = new Evaluation();
	        eval.setIntitule(intitule);
	        eval.setDescription(description);
	        eval.setCsv_file(file.getOriginalFilename());
	        eval.setDateDeCreation(new Date());
	        eval.setQuestions(questions);
	        evaluationRepo.save(eval);
		    
			String message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			LOGGER.error(e.toString());
			return ResponseEntity.badRequest().build();
		}
			
	}
	
	@PutMapping("/formateur/evaluations/edit")
	public ResponseEntity<Evaluation> editEvaluation(@RequestBody Evaluation Evaluation) {
		try {
			Evaluation newEvaluation = evaluationRepo.save(Evaluation);
			return ResponseEntity.ok(newEvaluation);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/formateur/evaluations/delete/{id}")
	public ResponseEntity<String> DeleteEvaluation(@PathVariable("id") int EvaluationId) {
		try {
			evaluationRepo.deleteById(EvaluationId);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
	
	
	
	
	
	


