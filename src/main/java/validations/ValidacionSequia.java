/**
 * 
 */
package validations;

import java.util.Iterator;

import context.Planeta;
import context.SistemaSolar;
import context.TipoPeriodo;

/**
 * @author ntoranzo
 * 
 * Si la diferencia de ángulos entre los planetas es de 0 grados o de 180 grados entonces los mismos se encuentran
 * alineados entre si y con el sol.
 *
 */
public class ValidacionSequia implements IValidacion {

	/* (non-Javadoc)
	 * @see validations.IValidacion#cumpleRegla(context.SistemaSolar, int)
	 */
	@Override
	public boolean cumpleRegla(SistemaSolar sistemaSolar, int numeroDia) {
		Iterator<Planeta> it = sistemaSolar.getPlanetas().iterator();
		int angAnterior = it.next().getAngulo(numeroDia);
		int angActual;
		int res;
		while (it.hasNext()) {
			angActual = it.next().getAngulo(numeroDia);
			res = Math.abs(angAnterior - angActual) % 360;
			if (res != 0 && res != 180) {
				return false;
			}
			angAnterior = angActual;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see validations.IValidacion#getTipoPeriodo()
	 */
	@Override
	public TipoPeriodo getTipoPeriodo() {
		return TipoPeriodo.SEQUIA;
	}

}
