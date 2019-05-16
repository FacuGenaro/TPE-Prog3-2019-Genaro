import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
	protected ArrayList<Aeropuerto> vertices;

	public Grafo() {
		this.vertices = new ArrayList<>();
	}

	public ArrayList<Aeropuerto> getVertices() {
		return new ArrayList<Aeropuerto>(vertices);
	}

	public void addVertice(Aeropuerto v) {
		// System.out.println(v.getNombre());
		if (!this.vertices.contains(v))
			this.vertices.add(v);
	}
	
	public void listarReservas() {
		for (Aeropuerto a : this.vertices) {
			for (Ruta r : a.getRutas()) {
				System.out.println("Para el vuelo desde " + r.getOrigen() + " hasta " + r.getDestino() + " Se hicieron las siguientes reservas " + r.getAerolineas());
			}
		}
	}

//	Verificar si existe un vuelo directo (es decir, sin escalas) entre un aeropuerto de origen y uno de
//	destino, para una aerolínea particular. De existir, se desea conocer los kilómetros que requiere el viaje
//	y la cantidad de asientos que se encuentran disponibles (es decir, no están reservados).
	public boolean servicioUno(String origen, String destino, String aerolinea) {
		for (Aeropuerto a : this.vertices) {
			if (a.getNombre().equals(origen)) {
				for (Ruta r : a.getRutas()) {
					if (r.getDestino().equals(destino)) {
						if (r.getAerolineas().containsKey(aerolinea)) {
							System.out.println("Existe un vuelo directo, la distancia es: " + r.getDistancia() + " y hay "
									+ r.getAerolineas().get(aerolinea).getAsientosDisponibles()
									+ " asientos disponibles");
							return true;
						}
						return false;
					}
				}
			}
		}
		return false;
	}

//	Para un par de aeropuertos de origen y destino, obtener todos los vuelos disponibles (directos o con
//	escalas) que se pueden tomar sin utilizar una aerolínea determinada. Para cada vuelo indicar la
//	aerolínea que se puede tomar, el número de escalas a realizar y la cantidad total de kilómetros a
//	recorrer.

	public void servicioDos(String origen, String destino) {
		HashMap<Aeropuerto, Boolean> visitados = new HashMap<>();
		int cantCaminos = 0;
		Float distanciaRecorrida = (float) 0;
		for (Aeropuerto a : this.vertices) {
			if (a.getNombre().equals(origen)) {
				List<List<Ruta>> rutas = DFS_Visitar(a, visitados, destino, new ArrayList<List<Ruta>>());
				for (List<Ruta> listaRutas : rutas) {
					cantCaminos++;
					int cantEscalas = 0;
					System.out.println("Camino n° " + cantCaminos + " para ir desde " + origen + " hasta " + destino);
					System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-");
					for (Ruta r : listaRutas) {
						// Escala 0 = origen
						System.out.println("Escala n°" + cantEscalas + " en " + r.getOrigen() + " que parte hacia " + r.getDestino());
						List<Aerolinea> aerolineasDisponibles = new ArrayList<>();
						for (String ae : r.getAerolineas().keySet()) {
							if (r.getAerolineas().get(ae).isDisponible()) {
								aerolineasDisponibles.add(r.getAerolineas().get(ae));
							}
						}
						System.out.println("Las aerolineas disponibles son las siguientes");
						System.out.println(aerolineasDisponibles);
						System.out.println();
						cantEscalas++;
						distanciaRecorrida = distanciaRecorrida + r.getDistancia();
					}
					System.out.println("La distancia para esta ruta fue: " + distanciaRecorrida + " Kilometros y hubo " + (cantEscalas-1) + " escalas");
					distanciaRecorrida = (float) 0;
					System.out.println("-.-.-.-.-.-.-.-.-.-.-.-.-");
				}
			}
		}
	}

	private List<List<Ruta>> DFS_Visitar(Aeropuerto ab, Map<Aeropuerto, Boolean> visitados, String destino,
			List<List<Ruta>> rutas) {
		List<Ruta> rutasActuales = new ArrayList<Ruta>();
		DFS_Visitar(ab, visitados, destino, rutas, rutasActuales);
		return rutas;
	}

	private void DFS_Visitar(Aeropuerto ab, Map<Aeropuerto, Boolean> visitados, String destino, List<List<Ruta>> rutas,
			List<Ruta> rutasActuales) {
		if (ab.getNombre().equals(destino)) {
			rutas.add(new ArrayList<Ruta>(rutasActuales));
			return;
		}
		if (!visitados.containsKey(ab)) {
			visitados.put(ab, true);
			for (Ruta r : ab.getRutas()) {
				rutasActuales.add(r);
				DFS_Visitar(r.getDestino(), visitados, destino, rutas, rutasActuales);
				rutasActuales.remove(r);
			}
		}
	}

//	Obtener todos los vuelos directos disponibles desde un país a otro, es decir, donde no se encuentren
//	reservados todos los asientos. Para cada vuelo se deberá indicar los aeropuertos de origen y de destino,
//	las aerolíneas con pasajes disponibles y la distancia en kilómetros.
	public void servicioTres(String paisOrigen, String paisDestino) {
		for (Aeropuerto a : this.vertices) {
			if (a.getPais().equals(paisOrigen)) {
				for (Ruta r : a.getRutas()) {
					if (r.getDestino().getPais().equals(paisDestino)) {
						for (String s : r.getAerolineas().keySet()) {
							if (r.getAerolineas().get(s).isDisponible()) {
								System.out.println("Vuelo disponible en la aerolinea " + s + " donde el origen es: "
										+ a.getNombre() + "-" + a.getPais() + " y el destino es: " + r.getDestino()
										+ "-" + r.getDestino().getPais());
							}
						}
					}
				}
			}
		}
	}
}