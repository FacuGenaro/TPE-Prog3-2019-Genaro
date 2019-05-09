import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	private static final String cvsSplitBy = ";";

	public static void parseCSVAeropuertos(Grafo g, String path) {
		String line = "";
		// Grafo aDevolver = new Grafo();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				// System.out.println("Agregados: " + items[0] + " " + items[1] + " " +
				// items[2]);
				// System.out.println("-------------");
				Aeropuerto a1 = new Aeropuerto(items[0], items[1], items[2]);
				g.addVertice(a1);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void parseCSVRutas(Grafo g, String path) {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((line = br.readLine()) != null) {
				Aeropuerto origen = null;
				Aeropuerto destino = null;

				String[] items = line.split(cvsSplitBy);
				for (Aeropuerto a : g.getVertices()) {
					if (a.getNombre().equals(items[0])) {
						origen = a;
					}
					if (a.getNombre().equals(items[1])) {
						destino = a;
					}
				}
				Ruta r = new Ruta();
				Float distancia = new Float(Float.valueOf(items[2]));
				boolean cabotaje = items[3].equals("1");
				r.setDistancia(distancia);
				r.setCabotaje(cabotaje);
				r.setOrigen(origen);
				r.setDestino(destino);
				origen.addRuta(r);
				Ruta r2 = new Ruta();
				r2.setDistancia(distancia);
				r2.setOrigen(destino);
				r2.setDestino(origen);
				r2.setCabotaje(cabotaje);
				destino.addRuta(r2);
				String[] aerolineasYAsientos = items[4].substring(1, items[4].length() - 1).split(",");
				for (String aerolineaYAsientos : aerolineasYAsientos) {
					String[] aya = aerolineaYAsientos.split("-");
					Integer asientosDisponibles = Integer.valueOf(aya[1]);
					Aerolinea a = new Aerolinea (asientosDisponibles, 0, aya[0]);
					r.addAerolinea(a.getNombre(), a);
					r2.addAerolinea(a.getNombre(), a);
					// Aerolinea nueva = new Aerolinea()
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void parseCSVReservas(Grafo g, String path) {
		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				for (Aeropuerto a : g.getVertices()) {
					if (a.getNombre().equals(items[0])) {
						for (Ruta r : a.getRutas()) {
							if (r.getDestino().equals(items[1])) {
								Integer valor = Integer.parseInt(items[3]);
								if (r.getAerolineas().containsKey(items[2])) {
									r.getAerolineas().get(items[2]).setAsientosReservados(valor);
								}
								//setear reservas en vez de hacer est
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Grafo g = new Grafo();
		parseCSVAeropuertos(g, "C:\\Users\\facun\\Desktop\\TPE Genaro\\datasets\\Aeropuertos.csv");
		parseCSVRutas(g, "C:\\Users\\facun\\Desktop\\TPE Genaro\\datasets\\Rutas.csv");
		parseCSVReservas(g, "C:\\Users\\facun\\Desktop\\TPE Genaro\\datasets\\Reservas.csv");

		int i = 1;
		for (Aeropuerto a : g.getVertices()) {
			for (Ruta r : a.getRutas()) {
				 System.out.println("Ciclo: " + i +"- Origen: " + r.getOrigen() + ", Destino:"
				 + r.getDestino() + ", Distancia: " + r.getDistancia() + ", Reservados: " + r.getAerolineas());
				 System.out.println("-----");
				 i++;
//				System.out.println(r.getAerolineas());
			}
		}

	}
}
