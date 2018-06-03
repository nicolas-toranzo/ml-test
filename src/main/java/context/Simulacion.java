/**
 * 
 */
package context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import validations.IValidacion;
import validations.ValidacionCOPYT;
import validations.ValidacionLluvia;
import validations.ValidacionSequia;

/**
 * @author ntoranzo
 *
 */
public final class Simulacion {
	public static final int NUMERO_DIAS_SIMULACION = 3650;
	private static SistemaSolar sistemaSolar;
	private static TipoPeriodo [] resultados = new TipoPeriodo[NUMERO_DIAS_SIMULACION];
	private static int numeroDiaMaximoLluvias;
	static {
		// Corro la simulación.
		List<IValidacion> validaciones = new ArrayList<>();
		ValidacionLluvia validacionLluvia = new ValidacionLluvia(); 
		validaciones.add(new ValidacionSequia());
		validaciones.add(new ValidacionCOPYT());
		validaciones.add(validacionLluvia);
		try {
			sistemaSolar = new SistemaSolar(
					Arrays.asList(new Planeta(500, 1, 0), new Planeta(1000, -5, 0), new Planeta(2000, 3, 0)), validaciones);
			for (int i = 0; i < NUMERO_DIAS_SIMULACION; i++) {
				resultados[i] = sistemaSolar.getTipoPeriodo(i);
			}
			numeroDiaMaximoLluvias = validacionLluvia.getNumeroDiaMaximoLluvias();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * @return the resultado
	 */
	public static TipoPeriodo getResultado(int numeroDia) {
		return resultados[numeroDia];
	}
	/**
	 * @return the numeroDiaMaximoLluvias
	 */
	public static int getNumeroDiaMaximoLluvias() {
		return numeroDiaMaximoLluvias;
	}
}
