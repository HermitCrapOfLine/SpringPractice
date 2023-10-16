package mongoex;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;

public class MongoEx6 {

	public static void main(String[] args) {

		try (MongoClient client = new MongoClient(new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2"));) {

			// 1. shop3 db에 연결
			MongoDatabase database = client.getDatabase("shop2");

			// 2.member collection에 연결
			MongoCollection<Document> collection = database.getCollection("member");

			// collection.drop(); // 컬렉션 제거

			Bson filter = new Document("id", "apple4");

			Bson all = Updates.combine(
					Updates.set("name", "happy"),
					Updates.set("tel", "5555")
			);

			collection.updateOne(filter, all);
			
			System.out.println("==== update 성공 ====");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
