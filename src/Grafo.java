import java.util.ArrayList;
public class Grafo {
	protected ArrayList<Aeropuerto> vertices;
	protected ArrayList<Ruta> rutas;

	//invertir rutas en el grafo para tener un grafo no dirigido.
	//Me gustaría hacerlo con mapa de mapa (como lo puedo hacer?)
	//Recorrer por la clave y segun la clave voy entrando en los vertices?
	
	public Grafo() {
		this.vertices = new ArrayList<>();
		this.rutas = new ArrayList<>();
	}
	
	public ArrayList<Aeropuerto> getVertices(){
		return new ArrayList<Aeropuerto>(vertices);
	}
	
	public void addVertice(Aeropuerto v) {
		if (!this.vertices.contains(v))
			this.vertices.add(v);
	}
	
	public ArrayList<Ruta> getRutas(){
		return new ArrayList<Ruta>(rutas);
	}
	
	public void addRuta(Ruta r) {
		if (!this.rutas.contains(r))
			this.rutas.add(r);
	}
}
