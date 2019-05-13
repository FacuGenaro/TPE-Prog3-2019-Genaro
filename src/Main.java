import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
					Aerolinea a = new Aerolinea(asientosDisponibles, 0, aya[0]);
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
								// setear reservas en vez de hacer est
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Integer pedirNumero() {
		Integer num = null;
		try {
			BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Ingrese la opcion deseada");
			num = new Integer(entrada.readLine());

		} catch (Exception exc) {
			System.out.println(exc);
		}
		return num;

	}

	public static void mostrarMenu() {
		System.out.println("1. Servicio 1: Verificar vuelos directos");
		System.out.println("2. Servicio 2: Obtener vuelos sin aerolinea");
		System.out.println("3. Servicio 3: Vuelos disponibles");
	}

	public static void main(String[] args) {
		Grafo g = new Grafo();
		parseCSVAeropuertos(g, "C:\\Users\\facun\\Desktop\\TPE Genaro\\datasets\\Aeropuertos.csv");
		parseCSVRutas(g, "C:\\Users\\facun\\Desktop\\TPE Genaro\\datasets\\Rutas.csv");
		parseCSVReservas(g, "C:\\Users\\facun\\Desktop\\TPE Genaro\\datasets\\Reservas.csv");

//		mostrarMenu();
//		int opcion = (pedirNumero());
//
//		switch (opcion) {
//		case 1: {
//			String origen = null;
//			String destino = null;
//			String aerolinea = null;
//			try {
//				BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
//				System.out.println("Ingrese su aeropuerto de origen");
//				origen = new String(entrada.readLine());
//				System.out.println("Ingrese su aeropuerto de destino");
//				destino = new String(entrada.readLine());
//				System.out.println("Ingrese la aerolinea en la que desea viajar");
//				aerolinea = new String(entrada.readLine());
//			} catch (Exception exc) {
//				System.out.println(exc);
//			}
//
//			g.servicioUno(origen, destino, aerolinea);
//			break;
//		}
//		case 2: {
//			System.out.println("Working on it");
//			break;
//
//		}
//		case 3: {
//			String paisOrigen = null;
//			String paisDestino = null;
//			try {
//				BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
//				System.out.println("Ingrese su pais de origen");
//				paisOrigen = new String(entrada.readLine());
//				System.out.println("Ingrese su pais de destino");
//				paisDestino = new String(entrada.readLine());
//			} catch (Exception exc) {
//				System.out.println(exc);
//			}
//
//			g.servicioTres(paisOrigen, paisDestino);
//		}
//		}
		
		//g.servicioDos("Pucon" , "Ministro Pistarini", "Delta");
		g.DFS();
	}

}
