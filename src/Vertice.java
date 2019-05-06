import java.util.ArrayList;

public class Vertice {
	private String aeropuerto;
	private String ubicacion;
	private ArrayList<Vertice> adyacencias;

	public Vertice(String aeropuerto, String ubicacion) {
		this.aeropuerto = aeropuerto;
		this.ubicacion = ubicacion;
		this.adyacencias = new ArrayList<>();
	}

	public void addAdyacencia(Vertice v) {
		this.adyacencias.add(v);
	}

	public ArrayList<Vertice> getAdyacencias() {
		return new ArrayList<>(this.adyacencias);
	}

	public String getAeropuerto() {
		return aeropuerto;
	}

	public void setAeropuerto(String aeropuerto) {
		this.aeropuerto = aeropuerto;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
