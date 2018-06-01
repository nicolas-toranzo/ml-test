/**
 * 
 */
package app;

import org.springframework.web.bind.annotation.RestController;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

import context.Planeta;
import context.SistemaSolar;
import context.TipoPeriodo;
import task.InitializeDatastoreDeferredTask;
import validations.IValidacion;
import validations.ValidacionCOPYT;
import validations.ValidacionLluvia;
import validations.ValidacionSequia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ntoranzo
 *
 */
@RestController
public class ServiceController {

	@RequestMapping("/resumen")
	public List<ResponseResumen> resumen() {
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
			// Corro la simulaci�n a lo largo de 3653 días terrestres (los pr�ximos 10 años; 3 son bisiestos)
			// y observo los resultados de acuerdo al enunciado del ejercicio.
			int[] contadorResultados = new int[TipoPeriodo.values().length];
			Arrays.fill(contadorResultados, 0);
			for (int i = 0; i < 3650; i++) {
				TipoPeriodo tipoPeriodo = sistemaSolar.getTipoPeriodo(i);
				contadorResultados[tipoPeriodo.getIndex()] += 1;
			}
			List<ResponseResumen> response = new ArrayList<ResponseResumen>();
			for (int i = 0; i < TipoPeriodo.values().length; i++) {
				response.add(new ResponseResumen(TipoPeriodo.getByIndex(i).toString(), contadorResultados[i]));
			}
			response.add(new ResponseResumen("El primer día del pico de lluvia se dió en el día #", validacionLluvia.getNumeroDiaMaximoLluvias()));
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/init")
	public void init() {
		// Inicializo el datastore: creo el job para que lo haga según lo solicitado.
		InitializeDatastoreDeferredTask task = new InitializeDatastoreDeferredTask();
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(com.google.appengine.api.taskqueue.TaskOptions.Builder.withPayload(task));
	}

	@RequestMapping("/clima")
	public Response index() {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		Key entityKey = datastore.newKeyFactory().setKind("Pronostico").newKey(1);
		Entity retrieved = datastore.get(entityKey);
		return new Response(1, retrieved.getString("clima"));
	}

}
