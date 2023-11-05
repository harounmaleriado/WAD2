package ewasteless.project.service;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductUpdateService {

    public static void uploadDataFromCSV(String path) {

        Firestore firestore = FirestoreClient.getFirestore();

        try (CSVParser csvParser = new CSVParser(Files.newBufferedReader(Paths.get(path)), CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            for (CSVRecord csvRecord : csvParser) {
                
                String brand = csvRecord.get("Brand");
                String model = csvRecord.get("Model");
                String benchmark = csvRecord.get("Benchmark");
                double noBenchmark = Double.valueOf(benchmark) ;

                Map<String, Object> data = new HashMap<>();
                data.put("brand", brand);
                data.put("model", model);
                data.put("benchmark", noBenchmark);
                
                // firestore will auto generate docuID
                firestore.collection("GPU").document().set(data).get();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Complete!");
    }
}

