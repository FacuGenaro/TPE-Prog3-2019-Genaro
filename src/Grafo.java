import java.util.ArrayList;

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

	public void recorrerGrafo() {
		// Hacer un mapa de ruta/boolean para marcar los recorridos
	}

//	Para un par de aeropuertos de origen y destino, obtener todos los vuelos disponibles (directos o con
//			escalas) que se pueden tomar sin utilizar una aerol�nea determinada. Para cada vuelo indicar la
//			aerol�nea que se puede tomar, el n�mero de escalas a realizar y la cantidad total de kil�metros a
//			recorrer.
	public void servicioDos(String origen, String destino, String aerolinea) {
		for (Aeropuerto a : this.vertices) {
			if (a.getNombre().equals(origen)) {
				for (Ruta r : a.getRutas()) {
					if (r.getDestino().equals(destino) && !r.getAerolineas().containsKey(aerolinea)) {

					}
				}
			}
		}
	}

//	Obtener todos los vuelos directos disponibles desde un pa�s a otro, es decir, donde no se encuentren
//	reservados todos los asientos. Para cada vuelo se deber� indicar los aeropuertos de origen y de destino,
//	las aerol�neas con pasajes disponibles y la distancia en kil�metros.
	public void servicioTres(String paisOrigen, String paisDestino) {
		// ArrayList<Aeropuerto> aDevolver = new ArrayList<>();
		for (Aeropuerto a : this.vertices) {
			if (a.getPais().equals(paisOrigen)) {
				for (Ruta r : a.getRutas()) {
					if (r.getDestino().getPais().equals(paisDestino)) {
						for (String s : r.getAerolineas().keySet()) {
							if (r.getAerolineas().get(s).isDisponible()) {
								System.out.println("Vuelo disponible en la aerolinea " + s + " donde el origen es: "
										+ a.getNombre() + " y el destino es: " + r.getDestino());
							}
						}
					}
				}
			}
		}
	}
}