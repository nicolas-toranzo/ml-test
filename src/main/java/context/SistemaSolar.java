/**
 * 
 */
package context;

import java.util.List;
import validations.IValidacion;

/**
 * @author ntoranzo
 *
 */
public class SistemaSolar {

	private final List<Planeta> planetas;
	private final List<IValidacion> validaciones;

	public SistemaSolar(List<Planeta> planetas, List<IValidacion> validaciones) throws Exception {
		if (planetas.size() < 3) {
			throw new Exception("El ejercicio solo tiene sentido para un m�nimo de 3 planetas dentro del sistema solar");
		}
		this.planetas = planetas;
		this.validaciones = validaciones;
	}

	/**
	 * @return the planetas
	 */
	public List<Planeta> getPlanetas() {
		return planetas;
	}

	/**
	 * Devuelve el tipo de per�odo en el que el sistema solar se encuentra en el d�a indicado.
	 * 
	 * @param numeroDia
	 * @return
	 */
	public TipoPeriodo getTipoPeriodo(int numeroDia) {
		for (IValidacion validacion : this.validaciones) {
			if (validacion.cumpleRegla(this, numeroDia))
				return validacion.getTipoPeriodo();
		}
		return TipoPeriodo.NINGUNO_EN_ESPECIAL;
	}

}
