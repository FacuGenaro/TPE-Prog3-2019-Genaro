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
				for (Aeropuerto a : g.getVertices()) {
					if (a.getNombre().equals(items[0])) {
						for (Aeropuerto aeropuertoOrigen : a.getAdyacencias()) {
							if (aeropuertoOrigen.getNombre().equals(items[1])) {
								for (Ruta r : aeropuertoOrigen.getRutas()) {
									if (r.getDestino().equals(items[1])) {
										Integer valor = Integer.parseInt(items[3]);
										r.addAerolinea(items[2], new Aerolinea(0,valor, items[2]));
										//r.getAerolineas().get(items[1]).setAsientosReservados(Integer.parseInt(items[3]));
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
