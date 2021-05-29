package fr.greta91.cda.agriotes.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import fr.greta91.cda.agriotes.model.Choix;
import fr.greta91.cda.agriotes.model.Question;

public class CSVHelper {
	public static String TYPE = "text/csv";
//	static String[] HEADERS = { "question", "choix", "choixCorrecte" };

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Question> csvToQuestions(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Question> questions = new ArrayList<Question>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Question question = new Question();
				question.setEnnonce(csvRecord.get("enonce"));
				
				String choixRaw = csvRecord.get("choix");
				String[] choixArray = choixRaw.split("\\|");// sépare la chaine de caractère en 4 choix séparé par un |
				for (String c : choixArray) {
					Choix choix = new Choix();
					choix.setChoix(c.trim());
					question.addChoix(choix);
				}
				
				Choix choixCorrect = new Choix();
				choixCorrect.setChoix(csvRecord.get("choixCorrect"));
				question.setChoixCorrect(choixCorrect);
				
				questions.add(question);
			}

			return questions;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

}
