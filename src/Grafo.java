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

	public List<Ruta> listarReservas() {
		ArrayList<Ruta> aDevolver = new ArrayList<>();
		for (Aeropuerto a : this.vertices) {
			for (Ruta r : a.getRutas()) {
				aDevolver.add(r);
			}
		}
		return aDevolver;
	}

//	Verificar si existe un vuelo directo (es decir, sin escalas) entre un aeropuerto de origen y uno de
//	destino, para una aerolínea particular. De existir, se desea conocer los kilómetros que requiere el viaje
//	y la cantidad de asientos que se encuentran disponibles (es decir, no están reservados).
	public ArrayList<String> servicioUno(String origen, String destino, String aerolinea) {
		ArrayList<String> aDevolver = new ArrayList<>();
		for (Aeropuerto a : this.vertices) {
			if (a.getNombre().equals(origen)) {
				for (Ruta r : a.getRutas()) {
					if (r.getDestino().equals(destino)) {
						if (r.getAerolineas().containsKey(aerolinea)) {
							aDevolver.add("Para ir desde " + origen + " hasta " + destino
									+ " existe un vuelo directo, la distancia es: " + r.getDistancia() + " y hay "
									+ r.getAerolineas().get(aerolinea).getAsientosDisponibles()
									+ " asientos disponibles");
						}
					}
				}
			}
		}
		return aDevolver;
	}

//	Para un par de aeropuertos de origen y destino, obtener todos los vuelos disponibles (directos o con
//	escalas) que se pueden tomar sin utilizar una aerolínea determinada. Para cada vuelo indicar la
//	aerolínea que se puede tomar, el número de escalas a realizar y la cantidad total de kilómetros a
//	recorrer.

	public List<List<Ruta>> servicioDos(String origen, String destino, String aerolinea) {
		HashMap<Aeropuerto, Boolean> visitados = new HashMap<>();
		List<List<Ruta>> rutas = new ArrayList<>();
		for (Aeropuerto a : this.vertices) {
			if (a.getNombre().equals(origen)) {
				rutas = DFS_Visitar(a, visitados, destino, new ArrayList<List<Ruta>>(), aerolinea);
			}
		}
		return rutas;
	}

	private List<List<Ruta>> DFS_Visitar(Aeropuerto ab, Map<Aeropuerto, Boolean> visitados, String destino,
			List<List<Ruta>> rutas, String aerolinea) {
		List<Ruta> rutasActuales = new ArrayList<Ruta>();
		DFS_Visitar(ab, visitados, destino, rutas, rutasActuales, aerolinea);
		return rutas;
	}

	private void DFS_Visitar(Aeropuerto ab, Map<Aeropuerto, Boolean> visitados, String destino, List<List<Ruta>> rutas,
			List<Ruta> rutasActuales, String aerolinea) {
		if (ab.getNombre().equals(destino)) {
			rutas.add(new ArrayList<Ruta>(rutasActuales));
			return;
		}
		if (!visitados.containsKey(ab)) {
			visitados.put(ab, true);
			for (Ruta r : ab.getRutas()) {
				if (r.getAerolineas().containsKey(aerolinea)) {
					Ruta rCopia = new Ruta(r.getDistancia(),r.isCabotaje(), r.getOrigen(), r.getDestino());
					for (String s : r.getAerolineas().keySet()) {
						if (!s.equals(aerolinea)) {
							rCopia.addAerolinea(s, r.getAerolineas().get(s));
						}
					}
					if (!rCopia.getAerolineas().isEmpty()) {
						rutasActuales.add(rCopia);
						DFS_Visitar(r.getDestino(), visitados, destino, rutas, rutasActuales, aerolinea);
						rutasActuales.remove(rCopia);
					}
				}else {	
					rutasActuales.add(r);
					DFS_Visitar(r.getDestino(), visitados, destino, rutas, rutasActuales, aerolinea);
					rutasActuales.remove(r);
				}
			}
		}
	}

//	Obtener todos los vuelos directos disponibles desde un país a otro, es decir, donde no se encuentren
//	reservados todos los asientos. Para cada vuelo se deberá indicar los aeropuertos de origen y de destino,
//	las aerolíneas con pasajes disponibles y la distancia en kilómetros.
	public ArrayList<String> servicioTres(String paisOrigen, String paisDestino) {
		ArrayList<String> aDevolver = new ArrayList<>();
		for (Aeropuerto a : this.vertices) {
			if (a.getPais().equals(paisOrigen)) {
				for (Ruta r : a.getRutas()) {
					if (r.getDestino().getPais().equals(paisDestino)) {
						for (String s : r.getAerolineas().keySet()) {
							if (r.getAerolineas().get(s).isDisponible()) {
								aDevolver.add("Vuelo disponible en la aerolinea " + s + " donde el origen es: "
										+ a.getNombre() + "-" + a.getPais() + " y el destino es: " + r.getDestino()
										+ "-" + r.getDestino().getPais());
							}
						}
					}
				}
			}
		}
		return aDevolver;
	}
}