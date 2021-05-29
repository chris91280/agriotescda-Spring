package fr.greta91.cda.agriotes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.greta91.cda.agriotes.model.Canal;
import fr.greta91.cda.agriotes.model.Evaluation;
import fr.greta91.cda.agriotes.repo.CanalRepository;


@CrossOrigin(maxAge = 3600, origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")

public class CanalController {
	
	@Autowired
	CanalRepository canalRepo;
	@GetMapping("/public/canaux")
	public List<Canal> getCanal(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber, 
											@RequestParam(value = "perPage", required = false, defaultValue = "10") int perPage, 
											@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord
											){
		Pageable page = PageRequest.of(pageNumber, perPage);
		List<Canal> list = null;
		if (searchWord.length() > 0) {
			list = canalRepo.findAllBynomCanalContainingIgnoreCase(searchWord, page);
		}
		else {
			list = canalRepo.findAll();
		}
		return list;
	}
	
	@GetMapping("/public/canaux/count")
	public HashMap<String, Integer> getCanauxCount(@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord
			) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (searchWord.length() > 0) {
			map.put("CanauxCount", canalRepo.getCanauxCountByNomCanal(searchWord));
		} else {
			map.put("CanauxCount", canalRepo.getCanauxCount());
		}
		
		return map;
	}

	@GetMapping("/public/canaux/{id}")
	public ResponseEntity<Canal> getCanal(@PathVariable int id) {
		Optional<Canal> optional = canalRepo.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/formateur/canaux/create")
	public ResponseEntity<Canal> createCanal(@RequestBody Canal canal ) {
		
		
		try {
			Canal newCanal = canalRepo.save(canal);
			return ResponseEntity.ok(newCanal);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
			
	}
	
	@PutMapping("/formateur/canaux/edit")
	public ResponseEntity<Canal> editCanal(@RequestBody Canal canal) {
		try {
			Canal newCanal = canalRepo.save(canal);
			return ResponseEntity.ok(newCanal);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/formateur/canaux/delete/{id}")
	public ResponseEntity<String> DeleteCanal(@PathVariable("id") int Canal_id) {
		try {
			canalRepo.deleteById(Canal_id);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
	
	
	
	
	

}
