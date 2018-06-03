package task;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;

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
	private static final int MAX_BATCH_SIZE = 100; 

	@Override
	public void run() {
		Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
		Queue queue = QueueFactory.getDefaultQueue();
		List<Entity> entities = new ArrayList<>();
		for (int i = 1; i <= Simulacion.NUMERO_DIAS_SIMULACION; i++) {
			Key entityKey = datastore.newKeyFactory().setKind(ENTITY_KIND).newKey(i);
			Entity entity = Entity.newBuilder(entityKey).set("clima", Simulacion.getResultado(i-1).toString()).build();
			entities.add(entity);
			if (i % MAX_BATCH_SIZE == 0) {
				queue.add(com.google.appengine.api.taskqueue.TaskOptions.Builder.withPayload(new DatastoreWriter(entities)));
				entities = new ArrayList<>();
			}
		}
		if (!entities.isEmpty()) {
			queue.add(com.google.appengine.api.taskqueue.TaskOptions.Builder.withPayload(new DatastoreWriter(entities)));
		}
	}
}
