import java.util.ArrayList;

public class Arista {
	private Integer distancia;
	private boolean cabotaje;
	private ArrayList<String> vuelos;
	private ArrayList<Aerolinea> aerolineas;
	private Vertice vertices[] = new Vertice[2];;
	
	public Arista(Integer distancia, boolean cabotaje, Vertice v1, Vertice v2) {
		this.vuelos = new ArrayList<>();
		this.distancia = distancia;
		this.cabotaje = cabotaje;
		this.aerolineas = new ArrayList<>();
		this.vertices[0] = v1;
		this.vertices[1] = v2;
	}

	public ArrayList<Aerolinea> getAerolineas() {
		return new ArrayList<>(this.aerolineas);
	}

	public void addAerolinea(Aerolinea a) {
		this.aerolineas.add(a);
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public ArrayList<String> getVuelos() {
		return new ArrayList<>(this.vuelos);
	}

	public void addVuelos(String v) {
		this.vuelos.add(v);
	}

	public boolean isCabotaje() {
		return cabotaje;
	}

	public void setCabotaje(boolean cabotaje) {
		this.cabotaje = cabotaje;
	}

}
