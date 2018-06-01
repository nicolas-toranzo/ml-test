/**
 * 
 */
package context;

import utils.Coordenadas;
import utils.MatematicasLocas;

/**
 * @author ntoranzo
 * 
 * Clase para definir un paneta dentro de nuestro sistema solar.
 *
 */
public class Planeta {

	private int distanciaDelSol;
	private int velocidad;
	private int anguloInicial;

	public Planeta(int distanciaDelSol, int velocidad, int anguloInicial) {
		this.distanciaDelSol = distanciaDelSol;
		this.velocidad = velocidad;
		this.anguloInicial = anguloInicial;
	}

	/**
	 * @return the distanciaDelSol
	 */
	public int getDistanciaDelSol() {
		return distanciaDelSol;
	}

	/**
	 * @param distanciaDelSol the distanciaDelSol to set
	 */
	public void setDistanciaDelSol(int distanciaDelSol) {
		this.distanciaDelSol = distanciaDelSol;
	}

	/**
	 * @return the velocidad
	 */
	public int getVelocidad() {
		return velocidad;
	}

	/**
	 * @param velocidad the velocidad to set
	 */
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	/**
	 * Devuelve la posición angular del planeta en el día indicado.
	 * 
	 * @param numeroDia
	 * @return
	 */
	public int getAngulo(int numeroDia) {
		int a = (numeroDia * this.velocidad + anguloInicial) % 360;
		return (a < 0 ? 360 + a : a);
	}

	/**
	 * Devuelve las coordenadas del planeta según el día indicado.
	 * 
	 * @param numeroDia
	 * @return
	 */
	public Coordenadas getCoordenadas(int numeroDia) {
		return new Coordenadas(MatematicasLocas.getProyeccionSobreEjeX(this.distanciaDelSol, this.getAngulo(numeroDia)),
				MatematicasLocas.getProyeccionSobreEjeY(distanciaDelSol, this.getAngulo(numeroDia)));
	}
}
