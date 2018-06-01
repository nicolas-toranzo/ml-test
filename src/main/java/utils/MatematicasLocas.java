/**
 * 
 */
package utils;

/**
 * @author ntoranzo
 *
 */
/**
 * @author AVX_NToranzo
 *
 */
public final class MatematicasLocas {

	/**
	 * Calcula la posición en el eje X.
	 * 
	 * @param distanciaDelSol
	 * @param angulo
	 * @return
	 */
	public static double getProyeccionSobreEjeX (int distanciaDelSol, int angulo) {
		return distanciaDelSol * Math.cos(angulo);
	}

	/**
	 * Calcula la posición en el eje Y.
	 * 
	 * @param distanciaDelSol
	 * @param angulo
	 * @return
	 */
	public static double getProyeccionSobreEjeY (int distanciaDelSol, int angulo) {
		return distanciaDelSol * Math.sin(angulo);
	}

	/**
	 * Determina si el punto (x,y) pertenece a la recta formada por los puntos (x1,y1) y (x2,y2).
	 * 
	 * @param x
	 * @param y
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static boolean puntoPerteneceARecta (double x, double y, double x1, double y1, double x2, double y2) {
		return ((x-x1) / (x2-x1)) == ((y-y1) / (y2-y1));
	}

	/**
	 * 	Devuelve la intersección del eje Y con la recta determinada por los puntos (x1,y1) y (x2,y2).
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getInterseccionConEjeY (double x1, double y1, double x2, double y2) {
		return (y1 - x1 * ((y2-y1) / (x2-x1)));
	}

	/**
	 * 	Devuelve la intersección del eje X con la recta determinada por los puntos (x1,y1) y (x2,y2).
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getInterseccionConEjeX (double x1, double y1, double x2, double y2) {
		return (x1 - y1 * ((x2-x1) / (y2-y1)));
	}

	/**
	 * Devuelve la distancia entre dos puntos.
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double getDistancia (double x1, double y1, double x2, double y2) {
		return Math.hypot(x1-x2, y1-y2);
	}

}
