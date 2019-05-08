
public class Aerolinea {
	private int asientosTotales;
	private int asientosReservados;
	private String nombre;

	public Aerolinea(int asientosTotales, int asientosReservados, String nombre) {
		super();
		this.asientosTotales = asientosTotales;
		this.asientosReservados = asientosReservados;
		this.nombre = nombre;
	}

	public int getAsientosTotales() {
		return asientosTotales;
	}

	public void setAsientosTotales(int asientosTotales) {
		this.asientosTotales = asientosTotales;
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

}
