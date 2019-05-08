import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderRutas {
	private static final String cvsSplitBy = ";";

	public static void parseCSVAristas(String path, Grafo g) {
		String line = "";
		// Grafo aDevolver = new Grafo();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((line = br.readLine()) != null) {
				// Ruta datos = new Ruta();
				String[] items = line.split(cvsSplitBy);
				for (Aeropuerto a : g.getVertices()) {
					if (a.getNombre().equals(items[0])) {
						for (Ruta r : a.getRutas()) {
							if (r.getDestino().equals(items[1])) {
								Float distancia = new Float(Float.valueOf(items[2]));
								r.setDistancia(distancia);
								if (items[3] == "0") {
									r.setCabotaje(false);
								} else {
									r.setCabotaje(true);
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return aDevolver;
	}
}
