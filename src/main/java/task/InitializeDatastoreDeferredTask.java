package task;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

import context.Simulacion;

/**
 * @author ntoranzo
 *
 *	DeferredTask de GAE para la carga del datastore con los datos de las predicciones
 *	para el sistema solar.
 *
 */
public class InitializeDatastoreDeferredTask implements DeferredTask{

	private static final long serialVersionUID = 7799689216120603102L;
	private static final String ENTITY_KIND = "Pronostico"; 

	@Override
	public void run() {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		// Primero limpio el Datastore.
		Query<Entity> query = Query.newEntityQueryBuilder().setKind(ENTITY_KIND).build();
		QueryResults<Entity> pronosticos = datastore.run(query);
		while (pronosticos.hasNext()) {
			Entity pronostico = pronosticos.next();
			datastore.delete(pronostico.getKey());
		}
		// Ahora cargo el Datastore con los datos de la nueva simulación.
		for (int i = 0; i < Simulacion.NUMERO_DIAS_SIMULACION; i++) {
			Key entityKey = datastore.newKeyFactory().setKind(ENTITY_KIND).newKey(i + 1);
			Entity entity = Entity.newBuilder(entityKey).set("clima", Simulacion.getResultado(i).toString()).build();
		    datastore.put(entity);
		}
	}
}
