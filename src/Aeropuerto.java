import java.util.ArrayList;

public class Aeropuerto {
	private String nombre;
	private String ciudad;
	private String pais;
	private ArrayList<Ruta> rutas;

	public Aeropuerto(String nombre, String ciudad, String pais) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.pais = pais;
		this.rutas = new ArrayList<>();
	}

	public ArrayList<Ruta> getRutas() {
		return new ArrayList<Ruta>(rutas);
	}

	public void addRuta(Ruta r) {
		if (!this.rutas.contains(r))
			this.rutas.add(r);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ciudad;
	}

	public void setUbicacion(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean equals(String a) {
		return (a.equals(this.getNombre()));
	}

	public String toString() {
		return (this.nombre);
	}
}
