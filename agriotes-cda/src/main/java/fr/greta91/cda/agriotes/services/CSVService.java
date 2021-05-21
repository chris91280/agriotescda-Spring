package fr.greta91.cda.agriotes.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.greta91.cda.agriotes.helper.CSVHelper;
import fr.greta91.cda.agriotes.model.Evaluation;
import fr.greta91.cda.agriotes.repo.EvaluationRepository;


@Service
public class CSVService {
  @Autowired
  EvaluationRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Evaluation> evaluations = CSVHelper.csvToEvaluations(file.getInputStream());
      repository.saveAll(evaluations);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public List<Evaluation> getAllEvaluations() {
    return repository.findAll();
  }
}