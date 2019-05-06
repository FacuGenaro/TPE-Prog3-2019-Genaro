import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	private static final String cvsSplitBy = ";";

	public static Grafo parseCSV(String path) {
		String line = "";
		Grafo aDevolver = new Grafo();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((line = br.readLine()) != null) {

				String[] items = line.split(cvsSplitBy);

				//acá armaría el grafo completo, pero para eso necesito tener algo en la clase Grafo

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//    public static void main(String[] args) {
//        String csvFile = "C:\\Users\\facun\\Desktop\\rutas.csv";
//        String line = "";
//        String cvsSplitBy = ";";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//
//            while ((line = br.readLine()) != null) {
//
//                String[] items = line.split(cvsSplitBy);
//
//                // ---------------------------------------------
//                // Poner el codigo para cargar los datos
//                // ---------------------------------------------
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

