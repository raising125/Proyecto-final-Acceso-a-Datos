package literals;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

public class literalMongoDB {
	private MongoCollection<Document> collection;
	public literalMongoDB() {
		Logger l = Logger.getLogger("org.mongodb");
		l.setLevel(Level.OFF);
	}
	
	//Fer print obtenguent el text de una colecció
	public String get_literal(String idi, String err) {
		Bson filter = Filters.and(Filters.eq("idi_cod",idi), Filters.eq("lit_clau", err.toUpperCase()));
		FindIterable<Document> iterable = collection.find(filter);

		MongoCursor<Document> cursor = iterable.iterator();		
		String res = (String) cursor.next().get("lit_text");
		return res;
	}
	
}
