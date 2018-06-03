/**
 * 
 */
package task;

import java.util.List;

import com.google.appengine.api.taskqueue.DeferredTask;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;

/**
 * @author ntoranzo
 *
 */
public class DatastoreWriter implements DeferredTask {

	private static final long serialVersionUID = -2656437688820777822L;
	private List<Entity> entities;

	public DatastoreWriter (List<Entity> entities) {
		this.entities = entities;
	}

	@Override
	public void run() {
		for (Entity e : entities) {
			Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
			datastore.put(e);
		}
	}

}
