package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBEx1 {
	
	public static void main(String[] args) {
		
		MongoClient mongoClient = new MongoClient(new MongoClientURI(
				"mongodb://winner:1234@localhost:27017/shop2"
			)
			);
		MongoDatabase database = mongoClient.getDatabase("shop2");
		
		MongoCollection<Document> collection = database.getCollection("memo");
		
		FindIterable<Document> result = collection.find();
		System.out.println(result.first());
	}
}
