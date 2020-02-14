package configuracio;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.hibernate.Session;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import entitatsHib.literal;

public class configuracioMongoDB {
	private MongoDatabase db;
	public configuracioMongoDB(MongoDatabase db) {
		this.db = db;
	}
	public void importarSesion(Session _session) {
		List<literal> result = (List<literal>) _session.createQuery("from literal").list();
		List<Document> lDoc = new ArrayList<Document>();
		{

			for (literal l : result) {
				Document oLit = new Document().append("lit_id", l.get_id()).append("idi_cod", l.get_idioma())
						.append("lit_clau", l.get_tipo_error()).append("lit_text", l.get_error());
				lDoc.add(oLit);

			}
		}
		MongoCollection<Document> collection = db.getCollection("literal");
		collection.insertMany(lDoc);

	}

	public void buscarConFiltro(String codigo) { // Busca el literal con el filtro que es el idioma y
																	// el codigo pasado por parámetro

		MongoCollection<Document> collection = db.getCollection("literal");
		Bson filter = Filters.and(Filters.eq("idi_cod", "es"), Filters.eq("lit_clau", codigo));
		FindIterable<Document> iterable2 = collection.find(filter);
		MongoCursor<Document> cursor2 = iterable2.iterator();

		System.out.println("------------");
		System.out.println(cursor2.next().get("lit_text"));

	}

	public void truncate() { // ELIMINA EL LOG
		MongoCollection<Document> collection = db.getCollection("log");
		collection.drop();
	}
	
	public void insertLog(String codigo) { // INSERTA codigo EN EL LOG con la actividad
		MongoCollection<Document> collection = db.getCollection("log");
		collection.insertOne(new Document().append("log", codigo));
		
	}
}
