/**
 * 
 */
package validations;

import context.SistemaSolar;
import context.TipoPeriodo;

/**
 * @author ntoranzo
 *
 */
public interface IValidacion {

	/**
	 * M�todo que devuelve si el sistema solar cumple las reglas impuestas por una validaci�n X.
	 * 
	 * @param sistemaSolar
	 * @param numeroDia
	 * @return
	 */
	public boolean cumpleRegla(SistemaSolar sistemaSolar, int numeroDia);

	/**
	 * Tipo de per�odo que eval�a la validaci�n actual.
	 * 
	 * @return
	 */
	public TipoPeriodo getTipoPeriodo();
}
