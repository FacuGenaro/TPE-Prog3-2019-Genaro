import java.util.HashMap;

public class Ruta {
	private Float distancia;
	private boolean cabotaje;
	private Aeropuerto origen;
	private Aeropuerto destino;
	private HashMap<String, Aerolinea> aerolineas;

	public Ruta() {
		this.origen = null;
		this.destino = null;
		this.aerolineas = new HashMap<>();
	}

	public Ruta(Float distancia, boolean cabotaje, Aeropuerto origen, Aeropuerto destino) {
		this.distancia = distancia;
		this.cabotaje = cabotaje;
		this.origen = origen;
		this.destino = destino;
		this.aerolineas = new HashMap<>();
	}

	public HashMap<String, Aerolinea> getAerolineas() {
		return new HashMap<>(this.aerolineas);
	}

	public void addAerolinea(String k, Aerolinea a) {
		this.aerolineas.put(k, a);
	}

	public Float getDistancia() {
		return distancia;
	}

	public void setDistancia(Float distancia) {
		this.distancia = distancia;
	}

	public Aeropuerto getOrigen() {
		return origen;
	}

	public void setOrigen(Aeropuerto origen) {
		this.origen = origen;
	}

	public Aeropuerto getDestino() {
		return destino;
	}

	public void setDestino(Aeropuerto destino) {
		this.destino = destino;
	}

	public boolean isCabotaje() {
		return cabotaje;
	}

	public void setCabotaje(boolean cabotaje) {
		this.cabotaje = cabotaje;
	}

	@Override
	public String toString() {
		String a = ("origen: " + this.origen.getNombre() + " destino: " + this.destino.getNombre() + " distancia: "
				+ this.distancia.toString() );
		return a;
	}
	
//	@Override
//	public String toString() {
//		String a = ("origen: " + this.origen.getNombre() + " destino: " + this.destino.getNombre());
//		return a;
//	}

}
