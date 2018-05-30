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
	 * Método que devuelve si el sistema solar cumple las reglas impuestas por una validación X.
	 * 
	 * @param sistemaSolar
	 * @param numeroDia
	 * @return
	 */
	public boolean cumpleRegla(SistemaSolar sistemaSolar, int numeroDia);

	/**
	 * Tipo de período que evalúa la validación actual.
	 * 
	 * @return
	 */
	public TipoPeriodo getTipoPeriodo();
}
