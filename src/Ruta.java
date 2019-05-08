//import java.util.ArrayList;
import java.util.HashMap;

public class Ruta {
	private Float distancia;
	private boolean cabotaje;
	private Aeropuerto origen;
	private Aeropuerto destino;
	//Está bien tener un map acá? hay algo más optimo? bien
	private HashMap<String,Aerolinea> aerolineas;
	
	
	public Ruta () {
		this.aerolineas = new HashMap<>();
	}
	public Ruta(Float distancia, boolean cabotaje, Aeropuerto origen, Aeropuerto destino) {
		this.distancia = distancia;
		this.cabotaje = cabotaje;
		this.origen = origen;
		this.destino = destino;
		this.aerolineas = new HashMap<>();
	}
	
	public void addReserva(String nombre, int reserva) {
		this.aerolineas.get(nombre).setAsientosReservados(reserva);
	}

	public HashMap<String,Aerolinea> getAerolineas() {
		return new HashMap<>(this.aerolineas);
	}

	public void addAerolinea(String k,Aerolinea a) {
		this.aerolineas.put(k,a);
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

}
