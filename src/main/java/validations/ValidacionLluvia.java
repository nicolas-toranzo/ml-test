/**
 * 
 */
package validations;

import context.SistemaSolar;
import context.TipoPeriodo;
import utils.Coordenadas;
import utils.MatematicasLocas;

/**
 * @author ntoranzo
 * 
 * Valido si los planetas forman un tri�ngulo conteniendo al sol dentro del mismo.
 * Interpretaci�n personal del enunciado: si el sol est� sobre el borde del triangulo
 * lo considero fuera del mismo.
 * Esto lo logro viendo las intersecciones del tri�ngulo resultante con los ejes:
 * el tri�ngulo se interseca con todos <=> el sol est� dentro del mismo.
 *
 */
public class ValidacionLluvia implements IValidacion {

	private static final byte EJE_MENOS_X = 0;
	private static final byte EJE_MAS_X = 1;
	private static final byte EJE_MENOS_Y = 2;
	private static final byte EJE_MAS_Y = 3;

	private int numeroDiaMaximoLluvias;
	private double perimetroMaximo;

	public ValidacionLluvia() {
		this.numeroDiaMaximoLluvias = -1;
		this.perimetroMaximo = 0;
	}

	/**
	 * @return the numeroDiaMaximoLluvias
	 */
	public int getNumeroDiaMaximoLluvias() {
		return numeroDiaMaximoLluvias;
	}

	/* (non-Javadoc)
	 * @see validations.IValidacion#cumpleRegla(context.SistemaSolar, int)
	 */
	@Override
	public boolean cumpleRegla(SistemaSolar sistemaSolar, int numeroDia) {
		Coordenadas[] p = { sistemaSolar.getPlanetas().get(0).getCoordenadas(numeroDia),
				sistemaSolar.getPlanetas().get(1).getCoordenadas(numeroDia),
				sistemaSolar.getPlanetas().get(2).getCoordenadas(numeroDia) };
		boolean[] interseccionCon = {false, false, false, false}; // X-, X+, Y-, Y+
		double perimetroActual = 0;
		for (byte i = 0; i < 3; i++) {
			// Intersecciones con el eje X.
			switch ((byte) Math.signum(MatematicasLocas.getInterseccionConEjeX(p[i].getX(), p[i].getY(),
					p[(i + 1) % 3].getX(), p[(i + 1) % 3].getY()))) {
			case -1:
				interseccionCon[EJE_MENOS_X] = true;
				break;
			case 1:
				interseccionCon[EJE_MAS_X] = true;
				break;
			}
			// Intersecciones con el eje Y.
			switch ((byte) Math.signum(MatematicasLocas.getInterseccionConEjeY(p[i].getX(), p[i].getY(),
					p[(i + 1) % 3].getX(), p[(i + 1) % 3].getY()))) {
			case -1:
				interseccionCon[EJE_MENOS_Y] = true;
				break;
			case 1:
				interseccionCon[EJE_MAS_Y] = true;
				break;
			}
			perimetroActual += MatematicasLocas.getDistancia(p[i].getX(), p[i].getY(),
					p[(i + 1) % 3].getX(), p[(i + 1) % 3].getY());
		}
		// Verifico si ya alcanc� las condiciones para que el sol est� en el centro del tri�ngulo formado por los 3 planetas.
		if (interseccionCon[EJE_MENOS_X] && interseccionCon[EJE_MAS_X] && interseccionCon[EJE_MENOS_Y] && interseccionCon[EJE_MAS_Y]) {
			// Valido si el actual es un nuevo m�ximo de lluvias.
			if (perimetroActual > perimetroMaximo) {
				perimetroMaximo = perimetroActual;
				numeroDiaMaximoLluvias = numeroDia;
			}
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see validations.IValidacion#getTipoPeriodo()
	 */
	@Override
	public TipoPeriodo getTipoPeriodo() {
		return TipoPeriodo.LLUVIA;
	}

}
