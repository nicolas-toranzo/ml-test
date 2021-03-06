/**
 * 
 */
package utils;

/**
 * @author ntoranzo
 * 
 * Clase para el manejo de las coordenadas de los planetas.
 *
 */
public class Coordenadas {
	private double x;
	private double y;

	public Coordenadas(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
}
