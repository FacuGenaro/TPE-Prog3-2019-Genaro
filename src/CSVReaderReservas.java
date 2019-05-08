import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderReservas {
	private static final String cvsSplitBy = ";";

	public static void parseCSVReservas(String path, Grafo g) {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				for (Ruta r : g.getRutas()) {
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
