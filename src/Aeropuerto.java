import java.util.ArrayList;

public class Aeropuerto {
	private String nombre;
	private String ciudad;
	private String pais;
	private ArrayList<Aeropuerto> adyacencias;
	private ArrayList<Aerolinea> aerolineas;
	private ArrayList<Ruta> rutas;
	//agregar rutas acá, meter aeropuertos adyacentes en las rutas

	//info de aeropuerto e info de rutas que salen de ahí
	
	public Aeropuerto(String nombre, String ciudad, String pais) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.pais = pais;
		this.adyacencias = new ArrayList<>();
		this.aerolineas = new ArrayList<>();
		this.rutas = new ArrayList<>();
	}
	
	public ArrayList<Ruta> getRutas(){
		return new ArrayList<Ruta>(rutas);
	}
	
	public void addRuta(Ruta r) {
		if (!this.rutas.contains(r))
			this.rutas.add(r);
	}
	
	public void addAerolinea(Aerolinea a) {
		this.aerolineas.add(a);
	}
	
	public ArrayList<Aerolinea> getAerolineas(){
		return new ArrayList<Aerolinea>(this.aerolineas);
	}
	
	public void addAdyacencia(Aeropuerto v) {
		this.adyacencias.add(v);
	}

	public ArrayList<Aeropuerto> getAdyacencias() {
		return new ArrayList<>(this.adyacencias);
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

}
