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

import fr.greta91.cda.agriotes.model.Groupe;
import fr.greta91.cda.agriotes.repo.GroupeRepository;

@CrossOrigin(maxAge = 3600, origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")


public class GroupeController {
	
	@Autowired
	GroupeRepository groupeRepo;
	@GetMapping("/public/groupes")
	public List<Groupe> getGroupe(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber, 
											@RequestParam(value = "perPage", required = false, defaultValue = "10") int perPage, 
											@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord
											){
		Pageable page = PageRequest.of(pageNumber, perPage);
		List<Groupe> list = null;
		if (searchWord.length() > 0) {
			list = groupeRepo.findAllByNomGroupeContainingIgnoreCase(searchWord, page);
		}
		else {
			list = groupeRepo.findAll();
		}
		return list;
	}
	
	@GetMapping("/public/groupes/count")
	public HashMap<String, Integer> getGroupesCount(@RequestParam(value = "searchWord", required = false, defaultValue = "") String searchWord
			) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		if (searchWord.length() > 0) {
			map.put("GroupesCount", groupeRepo.getGroupesCountByNom(searchWord));
		} else {
			map.put("GroupesCount", groupeRepo.getGroupesCount());
		}
		
		return map;
	}

	@GetMapping("/public/groupes/{id}")
	public ResponseEntity<Groupe> getGroupe(@PathVariable int id) {
		Optional<Groupe> optional = groupeRepo.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/formateur/groupes/create")
	public ResponseEntity<Groupe> createGroupe(@RequestBody Groupe Groupe ) {
		
		
		try {
			Groupe newGroupe = groupeRepo.save(Groupe);
			return ResponseEntity.ok(newGroupe);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
			
	}
	
	@PutMapping("/formateur/groupes/edit")
	public ResponseEntity<Groupe> editGroupe(@RequestBody Groupe Groupe) {
		try {
			Groupe newGroupe = groupeRepo.save(Groupe);
			return ResponseEntity.ok(newGroupe);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/formateur/groupes/delete/{id}")
	public ResponseEntity<String> DeleteGroupe(@PathVariable("id") int GroupeId) {
		try {
			groupeRepo.deleteById(GroupeId);
			return ResponseEntity.ok("OK");
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	

}
