import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderAeropuertos {
	private static final String cvsSplitBy = ";";

	public static Grafo parseCSVAeropuertos(String path) {
		String line = "";
		Grafo aDevolver = new Grafo();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				Aeropuerto a1 = new Aeropuerto(items[0], items[1], items[2]);
				aDevolver.addVertice(a1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aDevolver;
	}
}
