import java.math.BigDecimal;

public class CondicionMejorDistancia implements CondicionPoda{

	public int hacerPoda(BigDecimal distanciaActual, BigDecimal mejorDistancia) {
		return distanciaActual.compareTo(mejorDistancia);
	}
}
