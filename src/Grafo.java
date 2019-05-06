import java.util.ArrayList;
public class Grafo {
	protected ArrayList<Vertice> vertices;
	
	
	public Grafo() {
		this.vertices = new ArrayList<>();
	}
	
	public ArrayList<Vertice> getVertices(){
		return new ArrayList<Vertice>(vertices);
	}
	
	public void addVertice(Vertice v) {
		if (!this.vertices.contains(v))
			this.vertices.add(v);
	}
}
