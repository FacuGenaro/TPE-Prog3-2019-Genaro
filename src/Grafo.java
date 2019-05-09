import java.util.ArrayList;
public class Grafo {
	protected ArrayList<Aeropuerto> vertices;
	
	public Grafo() {
		this.vertices = new ArrayList<>();
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
