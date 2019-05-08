import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReaderRutas {
	private static final String cvsSplitBy = ";";

	public static void parseCSVAristas(String path, Grafo g) {
		String line = "";
		//Grafo aDevolver = new Grafo();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((line = br.readLine()) != null) {
				Ruta datos = new Ruta();
				String[] items = line.split(cvsSplitBy);
				for (Aeropuerto a : g.getVertices()) {
					if (a.getnombre().equals(items[0])) {
						datos.setOrigen(a);
					} else if (a.getnombre().equals(items[1])) {
						datos.setDestino(a);
					}
				}
				Float distancia = new Float (Float.valueOf(items[2]));
				datos.setDistancia(distancia);
				if (items[3] == "0") {
					datos.setCabotaje(false);
				}else {
					datos.setCabotaje(true);
				}
				
				g.addRuta(datos);
				//aDevolver.addRuta(datos);
				
				//Falta setear las aerolineas que hacen ese vuelo
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// return aDevolver;
	}
}
