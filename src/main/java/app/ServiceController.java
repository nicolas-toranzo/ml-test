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

import context.Simulacion;
import task.InitializeDatastoreDeferredTask;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ntoranzo
 *
 */
@RestController
public class ServiceController {

	@RequestMapping("/")
	public Map<String, Integer> resumen() {
		Map<String, Integer> mapResultados = new HashMap<String, Integer>();
		for (int i = 0; i < Simulacion.NUMERO_DIAS_SIMULACION; i++) {
			mapResultados.compute(Simulacion.getResultado(i).toString(), (k, v) -> (v == null) ? 1 : v + 1);
		}
		mapResultados.put("Primer día del máximo de lluvias: ", Simulacion.getNumeroDiaMaximoLluvias());
		return mapResultados;
	}
	
	@RequestMapping(value = "/init-datastore", method = RequestMethod.GET)
	public String init() {
		// Inicializo el datastore: creo el job para que lo haga según lo solicitado.
		InitializeDatastoreDeferredTask task = new InitializeDatastoreDeferredTask();
		Queue queue = QueueFactory.getDefaultQueue();
		queue.add(com.google.appengine.api.taskqueue.TaskOptions.Builder.withPayload(task));
		return "Tarea programada, el Datastore será inicializado";
	}

	private Response getResponseFromDatastore(int numeroDia) {
		if (numeroDia < 1 || numeroDia > Simulacion.NUMERO_DIAS_SIMULACION) {
			return new Response(EstadoRespuesta.ERROR, "Número de día fuera del rango de la simulación");
		}
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		Key entityKey = datastore.newKeyFactory().setKind("Pronostico").newKey(numeroDia);
		Entity retrieved = datastore.get(entityKey);
		return new Response(numeroDia, retrieved.getString("clima"));
	}

	@RequestMapping(value = "/clima", method = RequestMethod.GET)
	public Response clima(@RequestParam("dia") int numeroDia) {
		return getResponseFromDatastore(numeroDia);
	}

	@RequestMapping(value = "/clima2/{numeroDia}", method = RequestMethod.GET)
	public Response clima2(@PathVariable("numeroDia") int numeroDia) {
		return getResponseFromDatastore(numeroDia);
	}

}
