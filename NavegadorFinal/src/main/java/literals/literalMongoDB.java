package literals;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.hibernate.Session;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


public class literalMongoDB extends literals.literal{
	private MongoCollection<Document> collection;

	public literalMongoDB() {
		Logger l = Logger.getLogger("org.mongodb");
		l.setLevel(Level.OFF);
		populateMongo();
	}

	// Fer print obtenguent el text de una colecció
	public String get_literal(String idi, String err) {
		Bson filter = Filters.and(Filters.eq("idi_cod", idi), Filters.eq("lit_clau", err.toUpperCase()));
		FindIterable<Document> iterable = collection.find(filter);

		MongoCursor<Document> cursor = iterable.iterator();
		String res = (String) cursor.next().get("lit_text");
		return res;
	}

	public void populateMongo() {
		Session session = literalHib.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<entitatsHib.literal> result = (List<entitatsHib.literal>) session.createQuery("from literal").list();
		List<Document> lDoc = new ArrayList<Document>();

		for (entitatsHib.literal lit : result) {
			Document oLit = new Document().append("id", lit.get_id()).append("idi_cod", lit.get_idioma())
					.append("lit_clau", lit.get_tipo_error()).append("lit_text", lit.get_error());
			lDoc.add(oLit);
		}

		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("hibernate");

		collection = db.getCollection("literal");
		collection.drop();

		collection.insertMany(lDoc);

		session.close();
	}

}
