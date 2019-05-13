import java.util.ArrayList;
import java.util.HashMap;

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

	// Dados un origen y un destino verificar si existe un vuelo directo con una
	// aerolinea en particular
	public boolean servicioUno(String origen, String destino, String aerolinea) {
		for (Aeropuerto a : this.vertices) {
			if (a.getNombre().equals(origen)) {
				for (Ruta r : a.getRutas()) {
					if (r.getDestino().equals(destino)) {
						if (r.getAerolineas().containsKey(aerolinea)) {
							System.out.println("La distancia es: " + r.getDistancia() + " y hay "
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
	
	public void DFS() {
//		HashMap<Aeropuerto, Boolean> visitados = new HashMap<>();
//		for (Aeropuerto a : this.vertices) {
//			visitados.put(a, false);
//		}
		for (Aeropuerto a : this.vertices) {
			if (!a.getStatus()) {
				System.out.println("entrando desde " + a);
				DFS_Visitar (a);
			}
		}
	}
	
	public void DFS_Visitar(Aeropuerto ab) {
		ab.setStatus(true);
		for (Ruta r : ab.getRutas()) {
			if (!r.getDestino().getStatus()) {
				DFS_Visitar(r.getDestino());
				System.out.println("Visitado " + r.getDestino());
			}
		}
		
	}

//	Para un par de aeropuertos de origen y destino, obtener todos los vuelos disponibles (directos o con
//			escalas) que se pueden tomar sin utilizar una aerolínea determinada. Para cada vuelo indicar la
//			aerolínea que se puede tomar, el número de escalas a realizar y la cantidad total de kilómetros a
//			recorrer.
	public void servicioDos(String origen, String destino, String aerolinea) {
		HashMap<Ruta, Boolean> visitados = new HashMap<>();
		for (Aeropuerto a : this.vertices) {
			if (a.equals(origen)) {
				for (Ruta r : a.getRutas()) {
					if (r.getDestino().equals(destino)) {
						if (!r.getAerolineas().containsKey(aerolinea)) {
							if (!visitados.containsKey(r)) {
								for (String s : r.getAerolineas().keySet()) {
									System.out.println("Origen: " + a.getNombre() + ", destino: " + r.getDestino()
											+ ", aerolinea: " + r.getAerolineas().get(s).getNombre());
									visitados.put(r, true);
								}
							}
						}
					}

				}
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