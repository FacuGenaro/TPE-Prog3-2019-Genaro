
public class Aerolinea {
	private int asientosDisponibles;
	private int asientosReservados;
	private String nombre;

	public Aerolinea(int asientosDisponibles, int asientosReservados, String nombre) {
		super();
		this.asientosDisponibles = asientosDisponibles;
		this.asientosReservados = asientosReservados;
		this.nombre = nombre;
	}
	
	public boolean isDisponible() {
		return (this.asientosDisponibles >= this.asientosReservados);
	}
	
	public int getAsientosDisponibles() {
		return asientosDisponibles;
	}

	public void setAsientosDisponibles(int asientosDisponibles) {
		this.asientosDisponibles = asientosDisponibles;
	}

	public int getAsientosReservados() {
		return asientosReservados;
	}

	public void setAsientosReservados(int asientosReservados) {
		this.asientosReservados = asientosReservados;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
		return (this.nombre);
	}

}
