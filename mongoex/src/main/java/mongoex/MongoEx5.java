package mongoex;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx5 {

	public static void main(String[] args) {
		// 1. 몽고DB에 연결
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2"));

		// 2. shop3 db에 연결
		MongoDatabase database = client.getDatabase("shop2");

		// 3. member collection에 연결
		MongoCollection<Document> collection = database.getCollection("member");
		
		FindIterable<Document> result = collection.find();
		
		for (Document doc : result) {
			System.out.print("ID: " + doc.getString("id") + ", ");
			System.out.print("PW: " + doc.getString("pw") + ", ");
			System.out.print("NAME: " + doc.getString("name") + ", ");
			System.out.println("TEL: " + doc.getString("tel"));
			
		}
	}

}
