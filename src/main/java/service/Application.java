package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import context.Planeta;
import context.SistemaSolar;
import context.TipoPeriodo;
import validations.IValidacion;
import validations.ValidacionCOPYT;
import validations.ValidacionLluvia;
import validations.ValidacionSequia;

public class Application {

	/**
	 * Punto de entrada de la aplicaci�n.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Armo la lista de validaciones que voy a aplicar sobre el sistema solar.
			List<IValidacion> validaciones = new ArrayList<>();
			ValidacionLluvia validacionLluvia = new ValidacionLluvia(); 
			validaciones.add(new ValidacionSequia());
			validaciones.add(new ValidacionCOPYT());
			validaciones.add(validacionLluvia);
			// Instancio el sistema solar indicando la lista de planetas y las validaciones que aplicar� sobre el mismo.
			SistemaSolar sistemaSolar = new SistemaSolar(
					Arrays.asList(new Planeta(500, 1, 0), new Planeta(1000, -5, 0), new Planeta(2000, 3, 0)), validaciones);
			// Corro la simulaci�n a lo largo de 3653 d�as terrestres (los pr�ximos 10 a�os; 3 son bisiestos)
			// y observo los resultados de acuerdo al enunciado del ejercicio.
			int[] contadorResultados = new int[TipoPeriodo.values().length];
			Arrays.fill(contadorResultados, 0);
			for (int i = 0; i < 3650; i++) {
				TipoPeriodo tipoPeriodo = sistemaSolar.getTipoPeriodo(i);
				contadorResultados[tipoPeriodo.getIndex()] += 1;
			}
			for (int i = 0; i < TipoPeriodo.values().length; i++) {
				System.out.println(TipoPeriodo.getByIndex(i) + " : " + contadorResultados[i]);
			}
			System.out.println("El primer d�a del pico de lluvia se di� en el d�a #" + validacionLluvia.getNumeroDiaMaximoLluvias());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
