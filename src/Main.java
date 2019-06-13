import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static final String cvsSplitBy = ";";

	public static void parseCSVAeropuertos(Grafo g, String path) {
		String line = "";
		// Grafo aDevolver = new Grafo();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
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
				BigDecimal distancia = new BigDecimal(items[2]);
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
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void CSVWrite(ArrayList<String> salida) {
		BufferedWriter bw = null;
		try {
			File file1 = new File("C:\\Users\\facun\\Desktop\\TPE-Prog3-2019-Genaro\\datagets\\salida.csv");
			if (!file1.exists()) {
				file1.createNewFile();
			}
			FileWriter fw = new FileWriter(file1);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < salida.size(); i++) {
				String contenidoLinea1 = salida.get(i);
				bw.write(contenidoLinea1);
				bw.newLine();
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
			} catch (Exception ex) {
				System.out.println("Error cerrando el BufferedWriter" + ex);
			}
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
		System.out.println("1. Listar todos los aeropuertos");
		System.out.println("2. Listar todas las reservas realizadas");
		System.out.println("3. Servicio 1: Verificar vuelos directos");
		System.out.println("4. Servicio 2: Obtener todos los vuelos sin especificar aerolinea");
		System.out.println("5. Servicio 3: Vuelos disponibles de un pais a otro");
	}

	public static void main(String[] args) {
		Grafo g = new Grafo();
		ArrayList<String> salida = new ArrayList<>();

		parseCSVAeropuertos(g, "C:\\Users\\facun\\Desktop\\TPE-Prog3-2019-Genaro\\datasets\\AeropuertosSimplificado.csv");
		parseCSVRutas(g, "C:\\Users\\facun\\Desktop\\TPE-Prog3-2019-Genaro\\datasets\\RutasSimplificado.csv");
		parseCSVReservas(g, "C:\\Users\\facun\\Desktop\\TPE-Prog3-2019-Genaro\\datasets\\Reservas.csv");
		
		
		
//		List<List<Ruta>> ciclos = g.recorridoBacktracking("Ministro Pistarini");
//
//		for (List<Ruta> ciclo : ciclos) {
//			BigDecimal distancia = BigDecimal.ZERO;
//			for (Ruta ruta : ciclo) {
//				distancia = distancia.add(ruta.getDistancia());
//			}
//			System.out.println("\n OTRA RUTA");
//			System.out.println(ciclo);
//			System.out.println(distancia);
//		}
		
		System.out.println(g.greedy("Ministro Pistarini"));
		

//		mostrarMenu();
//		int opcion = (pedirNumero());
//
//		switch (opcion) {
//		case 1: {
//			System.out.println(g.getVertices());
//			break;
//		}
//		case 2: {
//			salida.clear();
//			for (Ruta r : g.listarReservas()) {
//				for (String a : r.getAerolineas().keySet()) {
//					if (r.getAerolineas().get(a).getAsientosReservados() > 0) {
//						String datosSalida = new String("Desde " + r.getOrigen() + " hasta " + r.getDestino() + " hay "
//								+ r.getAerolineas().get(a).getAsientosReservados() + " asientos reservados");
//						salida.add(datosSalida);
//						System.out.println(datosSalida);
//					}
//				}
//			}
//			CSVWrite(salida);
//			break;
//		}
//		case 3: {
//			salida.clear();
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
//			for (String s : g.servicioUno(origen, destino, aerolinea)) {
//				salida.add(s);
//			}
//			System.out.println(salida);
//			CSVWrite(salida);
//			break;
//		}
//		case 4: {
//			int contadorEscalas = 0;
//			Float distanciaTotal = (float) 0.0;
//			String origen = null;
//			String destino = null;
//			String aerolinea = null;
//			try {
//				BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
//				System.out.println("Ingrese su aeropuerto de origen");
//				origen = new String(entrada.readLine());
//				System.out.println("Ingrese su aeropuerto de destino");
//				destino = new String(entrada.readLine());
//				System.out.println("Ingrese la aerolinea que quiere evitar");
//				aerolinea = new String(entrada.readLine());
//			} catch (Exception exc) {
//				System.out.println(exc);
//			}
//
//			ArrayList<String> rutas = new ArrayList<String>();
//			for (List<Ruta> listaRutas : g.servicioDos(origen, destino, aerolinea)) {
//				for (Ruta r : listaRutas) {
//					distanciaTotal = distanciaTotal + r.getDistancia();
//					contadorEscalas++;
//					rutas.add("Origen " + r.getOrigen() + " destino " + r.getDestino() + " aerolineas "
//							+ r.getAerolineas());
//				}
//				rutas.add("Distancia total de la ruta: " + distanciaTotal + " Cantidad de escalas: " + contadorEscalas);
//				contadorEscalas = 0;
//				distanciaTotal = (float) 0.0;
//			}
//			for (String s : rutas) {
//				System.out.println(s);
//			}
//			CSVWrite(rutas);
//			break;
//
//		}
//		case 5: {
//			salida.clear();
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
//			for (String s : g.servicioTres(paisOrigen, paisDestino)) {
//				salida.add(s);
//			}
//			System.out.println(salida);
//			CSVWrite(salida);
//		}
//		}
	}

}
