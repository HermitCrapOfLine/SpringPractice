package mongoex;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoEx4 {

	public static void main(String[] args) {

		try(// 1. 몽고DB에 연결 try-with-resources 는 7버전 이상 
			MongoClient client = new MongoClient
			(new MongoClientURI("mongodb://winner:1234@localhost:27017/shop2"));
		) {
			// 2. shop3 db에 연결
			MongoDatabase database = client.getDatabase("shop2");

			// 3. member collection에 연결
			MongoCollection<Document> collection = database.getCollection("member");
			
			Document doc = new Document();
			doc.append("id", "apple4");
			doc.append("pw", "1234");
			doc.append("name", "apple4");
			doc.append("tel", "014");
			
			
			Document doc2 = new Document();
			doc.append("id", "apple5");
			doc.append("pw", "1234");
			doc.append("name", "apple5");
			doc.append("tel", "015");
			
			List<Document> list = new ArrayList<>();
			list.add(doc);
			list.add(doc2);
			
			collection.insertMany(list);
			System.out.println("insertMany 성공");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
