package task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

import context.Planeta;
import context.SistemaSolar;
import context.TipoPeriodo;
import validations.IValidacion;
import validations.ValidacionCOPYT;
import validations.ValidacionLluvia;
import validations.ValidacionSequia;

/**
 * @author ntoranzo
 *
 *	DeferredTask de GAE para la carga del datastore con los datos de las predicciones
 *	para el sistema solar.
 *
 */
public class InitializeDatastoreDeferredTask implements DeferredTask{

	private static final String ENTITY_KIND = "Pronostico"; 
	private static final int NUMERO_DIAS_SIMULACION = 3650;

	@Override
	public void run() {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		// Corro la simulación.
		List<IValidacion> validaciones = new ArrayList<>();
		ValidacionLluvia validacionLluvia = new ValidacionLluvia(); 
		validaciones.add(new ValidacionSequia());
		validaciones.add(new ValidacionCOPYT());
		validaciones.add(validacionLluvia);
		SistemaSolar sistemaSolar;
		try {
			sistemaSolar = new SistemaSolar(
					Arrays.asList(new Planeta(500, 1, 0), new Planeta(1000, -5, 0), new Planeta(2000, 3, 0)), validaciones);
			int[] contadorResultados = new int[TipoPeriodo.values().length];
			Arrays.fill(contadorResultados, 0);
			for (int i = 0; i < NUMERO_DIAS_SIMULACION; i++) {
				Key entityKey = datastore.newKeyFactory().setKind(ENTITY_KIND).newKey(i + 1);
			    Entity entity = Entity.newBuilder(entityKey)
			            .set("clima", sistemaSolar.getTipoPeriodo(i).toString())
			            .build();
			    datastore.put(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
