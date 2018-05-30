/**
 * 
 */
package validations;

import java.util.Iterator;

import context.Planeta;
import context.SistemaSolar;
import context.TipoPeriodo;
import utils.Coordenadas;
import utils.MatematicasLocas;

/**
 * @author ntoranzo
 * 
 * Primero obtengo una recta entre los dos primeros planetas y luego verifico que el resto pertenezca a la misma.
 *
 */
public class ValidacionCOPYT implements IValidacion {

	/* (non-Javadoc)
	 * @see validations.IValidacion#cumpleRegla(context.SistemaSolar, int)
	 */
	@Override
	public boolean cumpleRegla(SistemaSolar sistemaSolar, int numeroDia) {
		Iterator<Planeta> it = sistemaSolar.getPlanetas().iterator();
		Coordenadas p1 = it.next().getCoordenadas(numeroDia);
		Coordenadas p2 = it.next().getCoordenadas(numeroDia);
		while (it.hasNext()) {
			Coordenadas p = it.next().getCoordenadas(numeroDia);
			if (!MatematicasLocas.puntoPerteneceARecta(p.getX(), p.getY(), p1.getX(), p1.getY(), p2.getX(), p2.getY())) {
				return false;
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see validations.IValidacion#getTipoPeriodo()
	 */
	@Override
	public TipoPeriodo getTipoPeriodo() {
		return TipoPeriodo.COPYT;
	}

}
