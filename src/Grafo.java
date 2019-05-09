import java.util.ArrayList;
public class Grafo {
	protected ArrayList<Aeropuerto> vertices;
//	protected ArrayList<Ruta> rutas;

	//invertir rutas en el grafo para tener un grafo no dirigido.
	
	public Grafo() {
		this.vertices = new ArrayList<>();
//		this.rutas = new ArrayList<>();
	}
	
	public ArrayList<Aeropuerto> getVertices(){
		return new ArrayList<Aeropuerto>(vertices);
	}
	
	public void addVertice(Aeropuerto v) {
		//System.out.println(v.getNombre());
		if (!this.vertices.contains(v))
			this.vertices.add(v);
	}
	
	
}
