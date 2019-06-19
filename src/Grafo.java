import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Grafo {
	protected ArrayList<Aeropuerto> vertices;
	private CondicionPoda poda;
	private BigDecimal distanciaActual = BigDecimal.ZERO;
	private BigDecimal mejorDistancia = BigDecimal.valueOf(Double.MAX_VALUE);
	private int contadorRecursion = 0; // Las uso para las salidas por pantalla
	private int contadorPodaCumplida = 0;
	private int contadorVueltaAtras = 0;

	public Grafo() {
		this.vertices = new ArrayList<>();
	}

	public ArrayList<Aeropuerto> getVertices() {
		return new ArrayList<Aeropuerto>(vertices);
	}

	public Aeropuerto getVertice(String v) {
		for (Aeropuerto a : this.vertices) {
			if (a.equals(v)) {
				return a;
			}
		}
		return null;
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

	public void setCondicionPoda(CondicionPoda c) {
		this.poda = c;
	}

	// Verificar si existe un vuelo directo (es decir, sin escalas) entre un
	// aeropuerto de origen y uno de
	// destino, para una aerolínea particular. De existir, se desea conocer los
	// kilómetros que requiere el viaje
	// y la cantidad de asientos que se encuentran disponibles (es decir, no están
	// reservados).
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

	// Para un par de aeropuertos de origen y destino, obtener todos los vuelos
	// disponibles (directos o con
	// escalas) que se pueden tomar sin utilizar una aerolínea determinada. Para
	// cada vuelo indicar la
	// aerolínea que se puede tomar, el número de escalas a realizar y la cantidad
	// total de kilómetros a
	// recorrer.

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
					Ruta rCopia = new Ruta(r.getDistancia(), r.isCabotaje(), r.getOrigen(), r.getDestino());
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
				} else {
					rutasActuales.add(r);
					DFS_Visitar(r.getDestino(), visitados, destino, rutas, rutasActuales, aerolinea);
					rutasActuales.remove(r);
				}
			}
		}
	}

	// Obtener todos los vuelos directos disponibles desde un país a otro, es decir,
	// donde no se encuentren
	// reservados todos los asientos. Para cada vuelo se deberá indicar los
	// aeropuertos de origen y de destino,
	// las aerolíneas con pasajes disponibles y la distancia en kilómetros.
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

	// Problema del viajante Backtracking
	public List<List<Ruta>> getRutasBacktracking(String origen) {
		List<Aeropuerto> visitados = new ArrayList<>();
		List<List<Ruta>> rutas = new ArrayList<>();
		this.poda = new CondicionMejorDistancia();
		for (Aeropuerto a : this.vertices) {
			if (a.getNombre().equals(origen)) {
				rutas = DFS_Visitar(a, visitados, new ArrayList<List<Ruta>>());
			}
		}
		return rutas;
	}

	private List<List<Ruta>> DFS_Visitar(Aeropuerto ab, List<Aeropuerto> visitados, List<List<Ruta>> rutas) {
		List<Ruta> rutasActuales = new ArrayList<Ruta>();
		Aeropuerto origen = ab;
		DFS_Visitar(ab, visitados, origen, rutas, rutasActuales);
		return rutas;
	}

	private void DFS_Visitar(Aeropuerto ab, List<Aeropuerto> visitados, Aeropuerto origen, List<List<Ruta>> rutas,
			List<Ruta> rutasActuales) {
		this.contadorRecursion++;
		if (poda.hacerPoda(distanciaActual, mejorDistancia) == -1) {
			this.contadorPodaCumplida++;
			if (ab.getNombre().equals(origen.getNombre()) && visitados.containsAll(this.vertices)) {
				System.out.println("Se va a agregar la ruta " + rutasActuales);
				rutas.clear();
				mejorDistancia = distanciaActual;
				System.out.println("Ahora la mejor distancia es: " + mejorDistancia);
				System.out.println("Se entró recursivamente " + this.contadorRecursion
						+ " veces y la condición de poda se cumplió " + this.contadorPodaCumplida + " veces y se volvió atrás " + this.contadorVueltaAtras + " veces");
				System.out.println("---------------------- fin de ciclo ---------------");
				this.contadorRecursion = 0;
				this.contadorPodaCumplida = 0;
				this.contadorVueltaAtras = 0;
				rutas.add(new ArrayList<Ruta>(rutasActuales));
				return;
			}
			if (!visitados.contains(ab)) {
				visitados.add(ab);
				for (Ruta r : ab.getRutas()) {
					rutasActuales.add(r);
					distanciaActual = distanciaActual.add(r.getDistancia());
					DFS_Visitar(r.getDestino(), visitados, origen, rutas, rutasActuales);
					distanciaActual = distanciaActual.subtract(r.getDistancia());
					rutasActuales.remove(r);
				}
				visitados.remove(ab);
			}
		}
		this.contadorVueltaAtras ++;
		return;
	}

	// Problema del viajante Greedy

	public List<Ruta> getRutasGreedy(String origen) {
		List<Ruta> aDevolver = new ArrayList<>();
		List<Aeropuerto> aeropuertos = new ArrayList<>(this.greedy(origen));
		int cont = 1;
		for (Aeropuerto a : aeropuertos) {
			if (cont <= aeropuertos.size() - 1) {
				Aeropuerto siguiente = aeropuertos.get(cont);
				for (Ruta r : a.getRutas()) {
					if (r.getDestino().equals(siguiente)) {
						aDevolver.add(r);
					}
				}
			}
			cont = cont + 1;
		}
		return aDevolver;
	}

	public List<Aeropuerto> greedy(String origen) {
		List<Aeropuerto> queue = new ArrayList<>(this.vertices);
		List<Aeropuerto> aDevolver = new ArrayList<>();

		System.out.println("Queue inicial " + queue);

		aDevolver.add(this.getVertice(origen));
		Aeropuerto aeropuertoOrigen = this.getVertice(origen);
		Aeropuerto padre = aeropuertoOrigen;

		queue.remove(aeropuertoOrigen);
		while (!queue.isEmpty() && !this.isSolucion(aDevolver)) {
			System.out.println("Queue en cada ciclo: " + queue);
			Aeropuerto ae = this.seleccionarMejorCamino(queue, padre, aDevolver);
			padre = ae;
			queue.remove(ae);
			if (this.isFactible(ae, queue)) {
				aDevolver.add(ae);
			} else if (queue.isEmpty()) {
				for (Ruta r : ae.getRutas()) {
					if (r.getDestino().equals(aeropuertoOrigen)) {
						aDevolver.add(ae);
						aDevolver.add(r.getDestino());
						return aDevolver;
					}
				}
			} else {
				aDevolver.add(ae);
				return aDevolver;
			}
		}
		return aDevolver;
	}

	private Aeropuerto seleccionarMejorCamino(List<Aeropuerto> queue, Aeropuerto a, List<Aeropuerto> aDevolver) {
		System.out.println("Eligiendo el mejor camino a partir de " + a);
		System.out.println("Posibles caminos: " + a.getRutas());
		BigDecimal mejorDistancia = BigDecimal.valueOf(Double.MAX_VALUE);
		Aeropuerto aeropuerto = null;
		for (Ruta r : a.getRutas()) {
			if ((queue.contains(r.getDestino()) && (!aDevolver.contains(r.getDestino())))) {
				if (r.getDistancia().compareTo(mejorDistancia) == -1) {
					mejorDistancia = r.getDistancia();
					aeropuerto = r.getDestino();
				}
			}
		}
		System.out.println("La elección fue: " + aeropuerto + " cuya distancia es: " + mejorDistancia);
		return aeropuerto;
	}

	private Boolean isSolucion(List<Aeropuerto> a) {
		return (a.containsAll(this.vertices));
	}

	private Boolean isFactible(Aeropuerto a, List<Aeropuerto> queue) {
		for (Ruta r : a.getRutas()) {
			if (queue.contains(r.getDestino())) {
				System.out.println("La solucion encontrada es factible");
				System.out.println("---------------------- fin de ciclo ---------------");
				return true;
			}
		}
		System.out.println("La solucion encontrada no es factible ya que el aeropuerto " + a
				+ " no posee rutas con destinos sin visitar");
		System.out.println("---------------------- fin de ciclo ---------------");
		return false;
	}
}