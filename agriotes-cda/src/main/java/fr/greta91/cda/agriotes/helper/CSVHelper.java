package fr.greta91.cda.agriotes.helper;


	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.sql.Date;
	import java.util.ArrayList;
	import java.util.List;

	
	import org.springframework.web.multipart.MultipartFile;
	import org.apache.commons.csv.CSVFormat;
	import org.apache.commons.csv.CSVParser;
	import org.apache.commons.csv.CSVRecord;

	import fr.greta91.cda.agriotes.model.Evaluation;



	public class CSVHelper {
	  public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Id_evaluation", "dateDeCreation", "intitule", "note","csv_file" };

	  public static boolean hasCSVFormat(MultipartFile file) {

	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }

	  public static List<Evaluation> csvToEvaluations(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

	      List<Evaluation> Evaluations = new ArrayList<Evaluation>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	      for (CSVRecord csvRecord : csvRecords) {
	        Evaluation Evaluation = new Evaluation(
	              Integer.parseInt(csvRecord.get("Id")),
	              Date.valueOf(csvRecord.get("dateDeCreation")),
	              csvRecord.get("intitule"),
	              Integer.parseInt(csvRecord.get("note")) , 
	              csvRecord.get("csv_file")
	              
	            );

	        Evaluations.add(Evaluation);
	      }

	      return Evaluations;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }


}
